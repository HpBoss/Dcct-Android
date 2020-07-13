package com.example.dcct.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dcct.base.BaseFragment
import com.example.dcct.bean.BackResultData
import com.example.dcct.bean.LoginUserEntity
import com.example.dcct.bean.UserEntity
import com.example.dcct.databinding.FragmentLoginBinding
import com.example.dcct.presenter.LoginPresenter
import com.example.dcct.view.LoginCallback

class LoginFragment : BaseFragment(), LoginCallback {
    private var email: String? = null
    private var password: String? = null
    private var mInformationDetermine: InformationDetermine? = null
    private var mLoginPresenter: LoginPresenter? = null
    private var mBinding: FragmentLoginBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //检查输入邮箱格式
        checkEmailFormat(mBinding!!.textInputLayoutEmail.editText, mBinding!!.textInputLayoutEmail)
        //点击登录按钮
        mBinding!!.ivLogin.setOnClickListener { v: View? ->
            if (mBinding!!.textInputLayoutEmail.editText != null && mBinding!!.textInputLayoutPwd.editText != null) {
                email = mBinding!!.textInputLayoutEmail.editText!!.text.toString()
                password = mBinding!!.textInputLayoutPwd.editText!!.text.toString()
                if (email!!.contentEquals("") && !password!!.contentEquals("")) {
                    Toast.makeText(activity, "邮箱号为空", Toast.LENGTH_SHORT).show()
                } else if (!email!!.contentEquals("") && password!!.contentEquals("")) {
                    Toast.makeText(activity, "密码为空", Toast.LENGTH_SHORT).show()
                } else if (email!!.contentEquals("") && password!!.contentEquals("")) {
                    Toast.makeText(activity, "邮箱、密码均为空！", Toast.LENGTH_SHORT).show()
                } else {
                    //向服务器提交数据,将password进行MD5算法加密
//                    LoginUserEntity loginUserEntity = new LoginUserEntity( email, MD5Decode32( password ) );
                    val loginUserEntity = LoginUserEntity(email!!, password!!)
                    mLoginPresenter = LoginPresenter()
                    mLoginPresenter!!.attachView(this)
                    mLoginPresenter!!.fetchUserEntity(loginUserEntity)
                    //在onLoadLoginData方法中接受数据
                }
            }
        }
    }

    override fun onLoadLoginData(backData: BackResultData<UserEntity>) {
        if (backData.isState && activity != null) {
            Toast.makeText(activity, backData.msg, Toast.LENGTH_SHORT).show()
            //将登录成功的信息使用SharedPreferences存储
            var preferences = requireActivity().getSharedPreferences("SHARE_APP_LOGIN", Context.MODE_PRIVATE)
            preferences.edit().putBoolean("LOGIN_SUCCESS", true).apply()
            preferences = requireActivity().getSharedPreferences("SHARE_APP_DATA", Context.MODE_PRIVATE)
            preferences.edit().putString("nickname", backData.data!!.nickname)
                    .putLong("uid", backData.data!!.uid.toLong())
                    .apply()
            //向父活动发送登录成功的通知
            mInformationDetermine!!.loginSuccess()
        } else {
            Toast.makeText(activity, backData.msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        if (mLoginPresenter != null) {
            mLoginPresenter!!.detachView()
        }
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    interface InformationDetermine {
        fun loginSuccess()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        if (mInformationDetermine != null) {
            mInformationDetermine!!.loginSuccess()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mInformationDetermine = if (context is InformationDetermine) {
            context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement InformationDetermine")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mInformationDetermine = null
    }
}