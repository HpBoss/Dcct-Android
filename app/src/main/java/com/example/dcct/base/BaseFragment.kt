package com.example.dcct.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern
import kotlin.experimental.and

open class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return 加密后字符串密码
     */
    fun MD5Decode32(content: String): String {
        val hash: ByteArray
        hash = try {
            MessageDigest.getInstance("MD5").digest(content.toByteArray(StandardCharsets.UTF_8))
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("NoSuchAlgorithmException", e)
        }
        //对生成的16字节数组进行补零操作
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            if (b and 0xFF.toByte() < 0x10) {
                hex.append("0")
            }
            hex.append(Integer.toHexString((b and 0xFF.toByte()).toInt()))
        }
        return hex.toString()
    }

    /**
     * 检查输入邮箱格式
     * @param editText EditText
     * @param textInputLayout TextInputLayout
     */
    protected fun checkEmailFormat(editText: EditText?, textInputLayout: TextInputLayout) {
        if (editText != null) {
            editText.onFocusChangeListener = OnFocusChangeListener { v: View?, hasFocus: Boolean ->
                if (!hasFocus) {
                    if (!formatDecision(editText.text.toString())) {
                        textInputLayout.error = "邮箱格式错误"
                    } else {
                        textInputLayout.isErrorEnabled = false
                    }
                }
            }
        }
    }

    /**
     * 正则表达式检查邮箱格式
     * @param email 邮箱
     * @return 布尔值
     */
    private fun formatDecision(email: String): Boolean {
        val regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        val p = Pattern.compile(regEx1)
        val m = p.matcher(email)
        return m.matches()
    }
}