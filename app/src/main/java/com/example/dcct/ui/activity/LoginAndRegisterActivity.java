package com.example.dcct.ui.activity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dcct.ui.fragment.LoginFragment;
import com.example.dcct.R;
import com.example.dcct.ui.fragment.RegisterFragment;
import com.example.dcct.base.BaseActivity;

public class LoginAndRegisterActivity extends BaseActivity implements View.OnClickListener,
        LoginFragment.InformationDetermine,RegisterFragment.isRegisterListener {
    private TextView titleLogin;
    private TextView titleRegister;
    private TextPaint textPaintLogin;
    private TextPaint textPaintRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        setStatusBar(this,getResources().getColor(R.color.colorWhite),true);
        titleLogin = findViewById(R.id.title_login);
        titleRegister = findViewById(R.id.title_register);
        titleLogin.setOnClickListener(this);
        titleRegister.setOnClickListener(this);
        replaceFragment(new LoginFragment());
        textPaintLogin = titleLogin.getPaint();
        textPaintRegister = titleRegister.getPaint();

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
        titleRegister.setTextColor(getResources().getColor(R.color.colorWhite));
        titleLogin.setTextColor(getResources().getColor(R.color.colorBlack));

        titleRegister.setBackgroundResource(R.drawable.button_green);
        titleLogin.setBackgroundResource(R.drawable.button_white);

        textPaintLogin.setFakeBoldText(false);
        textPaintRegister.setFakeBoldText(true);
    }

    public void jumpToLogin() {
        replaceFragment(new LoginFragment());
        titleRegister.setTextColor(getResources().getColor(R.color.colorBlack));
        titleLogin.setTextColor(getResources().getColor(R.color.colorWhite));

        titleLogin.setBackgroundResource(R.drawable.button_green);
        titleRegister.setBackgroundResource(R.drawable.button_white);

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
