package com.example.dcct.ui.activity

import android.os.Bundle
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dcct.R
import com.example.dcct.base.BaseActivity
import com.example.dcct.databinding.ActivityLoginRegisterBinding
import com.example.dcct.ui.fragment.LoginFragment
import com.example.dcct.ui.fragment.LoginFragment.InformationDetermine
import com.example.dcct.ui.fragment.RegisterFragment
import com.example.dcct.ui.fragment.RegisterFragment.IsRegisterListener

class LoginAndRegisterActivity : BaseActivity(), View.OnClickListener, InformationDetermine, IsRegisterListener {
    private var textPaintLogin: TextPaint? = null
    private var textPaintRegister: TextPaint? = null
    private var mBinding: ActivityLoginRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        BaseActivity.setCustomDensity( this,getApplication());
        mBinding = ActivityLoginRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding!!.root)
        setStatusBar(this, resources.getColor(R.color.colorWhite))
        mBinding!!.titleLogin.setOnClickListener(this)
        mBinding!!.titleRegister.setOnClickListener(this)
        replaceFragment(LoginFragment())
        textPaintLogin = mBinding!!.titleLogin.paint
        textPaintRegister = mBinding!!.titleRegister.paint
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.title_login -> jumpToLogin()
            R.id.title_register -> jumpToRegister()
            else -> {
            }
        }
    }

    private fun jumpToRegister() {
        replaceFragment(RegisterFragment())
        mBinding!!.titleRegister.setTextColor(resources.getColor(R.color.colorWhite))
        mBinding!!.titleLogin.setTextColor(resources.getColor(R.color.colorBlack))
        mBinding!!.titleRegister.setBackgroundResource(R.drawable.button_green)
        mBinding!!.titleLogin.setBackgroundResource(R.drawable.button_white)
        textPaintLogin!!.isFakeBoldText = false
        textPaintRegister!!.isFakeBoldText = true
    }

    private fun jumpToLogin() {
        replaceFragment(LoginFragment())
        mBinding!!.titleRegister.setTextColor(resources.getColor(R.color.colorBlack))
        mBinding!!.titleLogin.setTextColor(resources.getColor(R.color.colorWhite))
        mBinding!!.titleLogin.setBackgroundResource(R.drawable.button_green)
        mBinding!!.titleRegister.setBackgroundResource(R.drawable.button_white)
        textPaintLogin!!.isFakeBoldText = true
        textPaintRegister!!.isFakeBoldText = false
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.down_fragment, fragment)
        transaction.commit()
    }

    override fun loginSuccess() {
        activityJump(this, MainActivity::class.java)
    }

    override fun setRegisterToLogin(state: Boolean) {
        if (state) {
            jumpToLogin()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}