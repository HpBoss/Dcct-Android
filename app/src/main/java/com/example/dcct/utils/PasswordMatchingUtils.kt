package com.example.dcct.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class PasswordMatchingUtils(private val password: TextInputLayout, private val passwordAgain: TextInputLayout, private val order: Int) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {
        if (order == 0) {
            if (Objects.requireNonNull(passwordAgain.editText)?.text.toString() != s.toString()) {
                passwordAgain.error = "两次输入密码不一致"
            } else {
                passwordAgain.isErrorEnabled = false
            }
        } else if (order == 1) {
            if (Objects.requireNonNull(password.editText)?.text.toString() != s.toString()) {
                passwordAgain.error = "两次输入密码不一致"
            } else {
                passwordAgain.isErrorEnabled = false
            }
        }
    }

}