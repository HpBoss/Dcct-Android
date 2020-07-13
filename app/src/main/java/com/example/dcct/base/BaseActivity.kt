package com.example.dcct.base

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.example.dcct.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(this, resources.getColor(R.color.colorPrimary))
    }
    /**
     * 给App所有界面添加一层灰度
     *
     * @param name
     * @param context
     * @param attrs
     * @return FrameLayout
     */
    /*@Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        try {
            if ("FrameLayout".equals( name )) {
                int count = attrs.getAttributeCount();
                for (int i = 0; i < count; i++) {
                    String attributeName = attrs.getAttributeName( i );
                    String attributeValue = attrs.getAttributeValue( i );
                    if (attributeName.equals( "id" )) {
                        int id = Integer.parseInt( attributeValue.substring( 1 ) );
                        String idVal = getResources().getResourceName( id );
                        if ("android:id/content".equals( idVal )) {
                            GrayFrameLayout grayFrameLayout = new GrayFrameLayout( context, attrs );
                            //当Activity的Window设置了BackGround
                            */
    /*grayFrameLayout.setBackground( getWindow().getDecorView().getBackground() );*/ /*
                            */
    /*grayFrameLayout.setWindow(getWindow());*/ /*
                            return grayFrameLayout;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView( name, context, attrs );
    }*/
    /**
     * 设置沉浸式界面
     * 根据Android版本的不同，作出相应设置
     */
    fun setImmersive() {
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 活动页面从小到大的渐变动画
     */
    fun alphaAnimation_smallToBig() {
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out)
    }

    /**
     * 活动页面从右至左平移滑动动画
     */
    fun translatingAnimation_rightToLeft() {
        overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out)
    }

    /**
     * 活动页面从左至右平移滑动动画
     */
    fun translatingAnimation_leftToRight() {
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out)
    }

    /**
     * @param start 起始活动
     * @param end   结束活动类
     */
    fun activityJump(start: Activity?, end: Class<*>?) {
        val intent = Intent(start, end)
        startActivity(intent)
        finish()
    }

    /**
     * @return 返回布尔值
     */
    val isLoginSuccess: Boolean
        get() {
            val preferences = getSharedPreferences("SHARE_APP_LOGIN", Context.MODE_PRIVATE)
            return preferences.getBoolean("LOGIN_SUCCESS", false)
        }

    /**
     * 设置状态栏颜色
     *
     * @param activity 活动
     * @param color    颜色值
     */
    protected fun setStatusBar(activity: Activity, color: Int) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //6.0 以上直接设置状态栏颜色
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = color
            // 去掉系统状态栏下的windowContentOverlay
            val v = window.findViewById<View>(android.R.id.content)
            if (v != null) {
                v.foreground = null
            }
            if (toGrey(color) > 225) { //判定该状态栏颜色条件下，是否要将状态栏图标切换成灰色
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0 以上直接设置状态栏颜色
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = color
        }
    }

    /**
     * 重写回退键监听，添加返回活动切换动画
     */
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out)
    }

    /**
     * 键盘焦点监听，焦点消失键盘收起
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus //这时能获取到焦点的View就是EditText
            if (isShouldHideKeyboard(v, ev)) {
                val imm = (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                imm.hideSoftInputFromWindow(v!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                v.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            //只要点击了EditText周围的空白处，就返回true
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    companion object {
        /**
         * 把颜色转换成灰度值。
         * 代码来自 Flyme 示例代码
         */
        fun toGrey(@ColorInt color: Int): Int {
            val blue = Color.blue(color)
            val green = Color.green(color)
            val red = Color.red(color)
            return red * 38 + green * 75 + blue * 15 shr 7
        }

        /**
         * 利用反射获取状态栏高度
         * @return statusBar的height
         */
        fun getStatusBarHeight(activity: Activity): Int {
            val resources = activity.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

        /**
         * 字节跳动屏幕适配方案
         */
        private var sNoncompatDensity = 0f
        var sNoncompatScaledDensity = 0f
        fun setCustomDensity(activity: Activity, application: Application) {
//        final DisplayMetrics appDisplayMetrics = Resources.getSystem().getDisplayMetrics();
            val appDisplayMetrics = application.resources.displayMetrics
            if (sNoncompatDensity == 0f) {
                sNoncompatDensity = appDisplayMetrics.density
                sNoncompatScaledDensity = appDisplayMetrics.scaledDensity
                //监听手机系统是否修改了字体大小，如果修改了就更新sNoncompatScaledDensity
                application.registerComponentCallbacks(object : ComponentCallbacks {
                    override fun onConfigurationChanged(newConfig: Configuration) {
                        if (newConfig.fontScale > 0) {
                            sNoncompatScaledDensity = application.resources.displayMetrics.scaledDensity
                        }
                    }

                    override fun onLowMemory() {}
                })
            }
            val targetDensity = appDisplayMetrics.widthPixels.toFloat() / 450
            val targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
            val targetDensityDpi = (160 * targetDensity).toInt()
            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.densityDpi = targetDensityDpi
            appDisplayMetrics.scaledDensity = targetScaledDensity
            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi
        }
    }
}