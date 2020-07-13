package com.example.dcct.utils

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dcct.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

object SnackBarUtil {
    private const val Info = 1
    const val Confirm = 2
    const val Warning = 3
    const val Alert = 4
    private const val red = -0xbbcca
    private const val green = -0xb350b0
    private const val blue = -0xde6a0d
    private const val orange = -0x3ef9

    /**
     * 短显示Snackbar，自定义颜色
     * @param view 控件
     * @param message 信息
     * @param messageColor 文字颜色
     * @param backgroundColor 背景色
     * @return 返回Snackbar
     */
    fun ShortSnackbar(view: View?, message: String?, messageColor: Int, backgroundColor: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT)
        setSnackbarColor(snackbar, messageColor, backgroundColor)
        return snackbar
    }

    /**
     * 长显示Snackbar，自定义颜色
     */
    fun LongSnackbar(view: View?, message: String?, messageColor: Int, backgroundColor: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
        setSnackbarColor(snackbar, messageColor, backgroundColor)
        return snackbar
    }

    /**
     * 自定义时常显示Snackbar，自定义颜色
     *
     */
    fun IndefiniteSnackbar(view: View?, message: String?, duration: Int, messageColor: Int, backgroundColor: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_INDEFINITE).setDuration(duration)
        setSnackbarColor(snackbar, messageColor, backgroundColor)
        return snackbar
    }

    /**
     * 短显示Snackbar，可选预设类型
     */
    fun ShortSnackbar(view: View?, message: String?, type: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT)
        switchType(snackbar, type)
        return snackbar
    }

    /**
     * 长显示Snackbar，可选预设类型
     */
    fun LongSnackbar(view: View?, message: String?, type: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
        switchType(snackbar, type)
        return snackbar
    }

    /**
     * 自定义时常显示Snackbar，可选预设类型
     */
    fun IndefiniteSnackbar(view: View?, message: String?, duration: Int, type: Int): Snackbar {
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_INDEFINITE).setDuration(duration)
        switchType(snackbar, type)
        return snackbar
    }

    //选择预设类型
    private fun switchType(snackbar: Snackbar, type: Int) {
        when (type) {
            Info -> setSnackbarColor(snackbar, blue)
            Confirm -> setSnackbarColor(snackbar, green)
            Warning -> setSnackbarColor(snackbar, orange)
            Alert -> setSnackbarColor(snackbar, Color.YELLOW, red)
        }
    }

    /**
     * 设置Snackbar背景颜色
     */
    fun setSnackbarColor(snackbar: Snackbar, backgroundColor: Int) {
        val view = snackbar.view
        view?.setBackgroundColor(backgroundColor)
    }

    /**
     * 设置Snackbar文字和背景颜色
     */
    fun setSnackbarColor(snackbar: Snackbar, messageColor: Int, backgroundColor: Int) {
        val view = snackbar.view
        if (view != null) {
            view.setBackgroundColor(backgroundColor)
            (view.findViewById<View>(R.id.snackbar_text) as TextView).setTextColor(messageColor)
        }
    }

    /**
     * 向Snackbar中添加view
     * @param index 新加布局在Snackbar中的位置
     */
    fun SnackbarAddView(snackbar: Snackbar, layoutId: Int, index: Int) {
        val snackbarview = snackbar.view
        val snackbarLayout = snackbarview as SnackbarLayout
        val add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId, null)
        val p = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        p.gravity = Gravity.CENTER_VERTICAL
        snackbarLayout.addView(add_view, index, p)
    }
}