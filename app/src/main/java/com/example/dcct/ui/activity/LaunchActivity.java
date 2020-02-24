package com.example.dcct.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.dcct.R;
import com.example.dcct.base.BaseActivity;

public class LaunchActivity extends BaseActivity {
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed( () -> {
            preferences = getSharedPreferences("SHARE_APP_TAG", Context.MODE_PRIVATE );
            if (isFirstStart()){
                activityJump(LaunchActivity.this, GuideActivity.class);
                translatingAnimation_rightToLeft();
            }else {
                if (isLoginSuccess()){//判断之前是否登录成功
                    activityJump(LaunchActivity.this, MainActivity.class);
                }else {
                    activityJump(LaunchActivity.this, LoginAndRegisterActivity.class);
                    translatingAnimation_rightToLeft();
                }
            }
        },3000);
    }

    /**
     * 获取SharedPreferences值，存在则打开它，否则创建新的SharedPreferences对象值
     * getBoolean(key，defValue)：key是键值，defValue是初始默认值
     * @return 通过返回布尔值反应是否是安装过后第一次启动
     */
    public boolean isFirstStart() {
        boolean isFirst = preferences.getBoolean("FIRST_START", true);
        if (isFirst) {
            preferences.edit().putBoolean("FIRST_START", false).apply();
            return true;
        } else {
            return false;
        }
    }

}
