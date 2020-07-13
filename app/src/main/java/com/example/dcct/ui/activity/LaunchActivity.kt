package com.example.dcct.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import com.example.dcct.base.BaseActivity
import com.example.dcct.databinding.ActivityLaunchBinding

class LaunchActivity : BaseActivity() {
    private var preferences: SharedPreferences? = null
    private var mBinding: ActivityLaunchBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        BaseActivity.setCustomDensity( this,getApplication());
        setImmersive()
        mBinding = ActivityLaunchBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding!!.root)
        Handler().postDelayed({
            preferences = getSharedPreferences("SHARE_APP_TAG", Context.MODE_PRIVATE)
            if (isFirstStart) {
                activityJump(this@LaunchActivity, GuideActivity::class.java)
                translatingAnimation_rightToLeft()
            } else {
                if (isLoginSuccess) { //判断之前是否登录成功
                    activityJump(this@LaunchActivity, MainActivity::class.java)
                } else {
                    activityJump(this@LaunchActivity, LoginAndRegisterActivity::class.java)
                    translatingAnimation_rightToLeft()
                }
            }
        }, 3000)
    }

    /**
     * 获取SharedPreferences值，存在则打开它，否则创建新的SharedPreferences对象值
     * getBoolean(key，defValue)：key是键值，defValue是初始默认值
     * @return 通过返回布尔值反应是否是安装过后第一次启动
     */
    private val isFirstStart: Boolean
        get() {
            val isFirst = preferences!!.getBoolean("FIRST_START", true)
            return if (isFirst) {
                preferences!!.edit().putBoolean("FIRST_START", false).apply()
                true
            } else {
                false
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}