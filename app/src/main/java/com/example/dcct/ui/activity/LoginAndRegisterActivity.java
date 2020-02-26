package com.example.dcct.ui.activity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dcct.databinding.ActivityLoginRegisterBinding;
import com.example.dcct.ui.fragment.LoginFragment;
import com.example.dcct.R;
import com.example.dcct.ui.fragment.RegisterFragment;
import com.example.dcct.base.BaseActivity;

public class LoginAndRegisterActivity extends BaseActivity implements View.OnClickListener,
        LoginFragment.InformationDetermine,RegisterFragment.isRegisterListener {

    private TextPaint textPaintLogin;
    private TextPaint textPaintRegister;
    private ActivityLoginRegisterBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BaseActivity.setCustomDensity( this,getApplication());
        mBinding = ActivityLoginRegisterBinding.inflate( LayoutInflater.from( this ) );
        setContentView(mBinding.getRoot());

        setStatusBar(this,getResources().getColor(R.color.colorWhite),true);

        mBinding.titleLogin.setOnClickListener(this);
        mBinding.titleRegister.setOnClickListener(this);
        replaceFragment(new LoginFragment());
        textPaintLogin = mBinding.titleLogin.getPaint();
        textPaintRegister = mBinding.titleRegister.getPaint();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_login:
                jumpToLogin();
                break;
            case R.id.title_register:
                jumpToRegister();
                break;
            default:
                break;
        }
    }

    private void jumpToRegister() {
        replaceFragment(new RegisterFragment());
        mBinding.titleRegister.setTextColor(getResources().getColor(R.color.colorWhite));
        mBinding.titleLogin.setTextColor(getResources().getColor(R.color.colorBlack));

        mBinding.titleRegister.setBackgroundResource(R.drawable.button_green);
        mBinding.titleLogin.setBackgroundResource(R.drawable.button_white);

        textPaintLogin.setFakeBoldText(false);
        textPaintRegister.setFakeBoldText(true);
    }

    public void jumpToLogin() {
        replaceFragment(new LoginFragment());
        mBinding.titleRegister.setTextColor(getResources().getColor(R.color.colorBlack));
        mBinding.titleLogin.setTextColor(getResources().getColor(R.color.colorWhite));

        mBinding.titleLogin.setBackgroundResource(R.drawable.button_green);
        mBinding.titleRegister.setBackgroundResource(R.drawable.button_white);

        textPaintLogin.setFakeBoldText(true);
        textPaintRegister.setFakeBoldText(false);
    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.down_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void loginSuccess() {
        activityJump(this, MainActivity.class);
    }

    @Override
    public void setRegisterToLogin(boolean state) {
        if (state) {
            jumpToLogin();
        }
    }
}
