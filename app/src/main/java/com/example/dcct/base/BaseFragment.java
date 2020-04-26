package com.example.dcct.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView( inflater, container, savedInstanceState );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
    }

    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return 加密后字符串密码
     */
    public String MD5Decode32(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 检查输入邮箱格式
     * @param editText EditText
     * @param textInputLayout TextInputLayout
     */
    protected void checkEmailFormat(EditText editText, TextInputLayout textInputLayout){
        if (editText != null) {
            editText.setOnFocusChangeListener( (v, hasFocus) -> {
                if (!hasFocus) {
                    if (!formatDecision( editText.getText().toString() )) {
                        textInputLayout.setError( "邮箱格式错误" );
                    } else {
                        textInputLayout.setErrorEnabled( false );
                    }
                }
            } );
        }
    }

    /**
     * 正则表达式检查邮箱格式
     * @param email 邮箱
     * @return 布尔值
     */
    private boolean formatDecision(String email) {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile( regEx1 );
        Matcher m = p.matcher( email );
        return m.matches();
    }
}
