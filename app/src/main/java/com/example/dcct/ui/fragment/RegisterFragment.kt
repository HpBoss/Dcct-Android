package com.example.dcct.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dcct.base.BaseFragment
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.RegisterUserEntity
import com.example.dcct.databinding.FragmentRegisterBinding
import com.example.dcct.presenter.RegisterPresenter
import com.example.dcct.utils.PasswordMatchingUtils
import com.example.dcct.utils.SnackBarUtil
import com.example.dcct.view.RegisterCallback
import java.util.*

class RegisterFragment : BaseFragment(), RegisterCallback {
    private var password: String? = null
    private var username: String? = null
    private var email: String? = null
    private var mRegisterPresenter: RegisterPresenter? = null
    private var mIsRegisterListener: IsRegisterListener? = null
    private var mBinding: FragmentRegisterBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentRegisterBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //检查输入邮箱格式
        checkEmailFormat(mBinding!!.textInputLayoutEmail.editText, mBinding!!.textInputLayoutEmail)
        //检测两个输入框中输入的内容是否一致（情景：当第一次密码输入完成）
        if (mBinding!!.textInputLayoutPwdr.editText != null) {
            mBinding!!.textInputLayoutPwdr.editText!!.addTextChangedListener(PasswordMatchingUtils(mBinding!!.textInputLayoutPwdr, mBinding!!.textInputLayoutPwdAgain, 0))
        }
        //同样需要再次检测两个输入框中的内容是否相同（情景：第二次输入密码，并点击注册按钮）
        if (mBinding!!.textInputLayoutPwdAgain.editText != null) {
            mBinding!!.textInputLayoutPwdAgain.editText!!.addTextChangedListener(
                    PasswordMatchingUtils(mBinding!!.textInputLayoutPwdr, mBinding!!.textInputLayoutPwdAgain, 1))
            mBinding!!.ivRegister.setOnClickListener { v: View? ->
                username = Objects.requireNonNull(mBinding!!.textInputLayoutUserName.editText)?.text.toString()
                email = Objects.requireNonNull(mBinding!!.textInputLayoutEmail.editText)?.text.toString()
                password = Objects.requireNonNull(mBinding!!.textInputLayoutPwdr.editText)?.text.toString()
                if (username == "" || email == "" || password == "") {
                    SnackBarUtil.ShortSnackbar(mBinding!!.snackBarHint, "信息输入不完整！！！", SnackBarUtil.Warning).show()
                } else {
                    //向服务器提交数据
                    val registerUserEntity = RegisterUserEntity(username!!, email!!, MD5Decode32(password!!))
                    mRegisterPresenter = RegisterPresenter()
                    mRegisterPresenter!!.attachView(this)
                    mRegisterPresenter!!.fetchRegisterResult(registerUserEntity)
                    //在onLoadRegisterData方法中接受服务器返回的数据
                }
            }
        }
    }

    override fun onLoadRegisterData(backData: BackResultData<*>) {
        if (backData != null) {
            if (backData.isState) {
                SnackBarUtil.ShortSnackbar(mBinding!!.snackBarHint, "恭喜你，注册成功！！！", SnackBarUtil.Confirm).show()
                mIsRegisterListener!!.setRegisterToLogin(true)
            } else {
                SnackBarUtil.ShortSnackbar(mBinding!!.snackBarHint, backData.msg, SnackBarUtil.Alert).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        if (mRegisterPresenter != null) {
            mRegisterPresenter!!.detachView()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mIsRegisterListener = if (context is IsRegisterListener) {
            context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement isRegisterListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mIsRegisterListener = null
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    interface IsRegisterListener {
        fun setRegisterToLogin(state: Boolean)
    }
}