package com.example.dcct.utils;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class InputContentListenerUtils implements TextWatcher {
    private TextInputLayout password;
    private TextInputLayout passwordAgain;
    private int order;

    public InputContentListenerUtils(TextInputLayout password, TextInputLayout passwordAgain,int order) {
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.order = order;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (order == 0) {
            if (!passwordAgain.getEditText().getText().toString().equals(s.toString())) {
                passwordAgain.setError("两次输入密码不一致");
            }else {
                passwordAgain.setErrorEnabled(false);
            }
        }else if (order == 1){
            if (!password.getEditText().getText().toString().equals(s.toString())) {
                passwordAgain.setError("两次输入密码不一致");
            }else {
                passwordAgain.setErrorEnabled(false);
            }
        }

    }
}
