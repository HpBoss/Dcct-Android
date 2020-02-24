package com.example.dcct.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dcct.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        ActionBar actionbar = getSupportActionBar();
//        if(actionbar != null){
//            actionbar.hide();
//        }
    }

    /**
     * 活动页面从小到大的渐变动画
     */
    public void alphaAnimation_smallToBig(){
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }
    /**
     * 活动页面从右至左平移滑动动画
     */
    public void translatingAnimation_rightToLeft(){
        overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out);
    }

    /**
     * 活动页面从左至右平移滑动动画
     */
    public void translatingAnimation_leftToRight(){
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out);
    }

    /**
     *
     * @param start 起始活动
     * @param end 结束活动类
     */
    public void activityJump(Activity start,Class end){
        Intent intent = new Intent(start, end);
        startActivity(intent);
        finish();
    }

    /**
     *
     * @return 返回布尔值
     */
    public boolean isLoginSuccess(){
        SharedPreferences preferences = getSharedPreferences("SHARE_APP_LOGIN", Context.MODE_PRIVATE);
        return preferences.getBoolean("LOGIN_SUCCESS", false);
    }


    /**
     * 添加状态栏占位视图
     * @param activity 传入的活动对象
     * @param color 状态栏背景色,如果为0，则为全屏显示
     * @param useStatusBarColor 状态栏字体颜色是否设置成深色
     */
    public static void setStatusBar(Activity activity, int color, boolean useStatusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //Build.VERSION_CODES.LOLLIPOP 5.0
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            if (color != 0) {
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorWhite));
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected void addStatusViewWithColor(Activity activity, int color) {
        //设置 paddingTop
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setPadding(0, getStatusBarHeight(), 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 以上直接设置状态栏颜色
            activity.getWindow().setStatusBarColor(color);
        } else {
            //根布局添加占位状态栏
            ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight());
            statusBarView.setBackgroundColor(color);
            decorView.addView(statusBarView, lp);
        }
    }
    /**
     * 重写回退键监听，添加返回活动切换动画
     */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out);
    }
    /**
     *键盘焦点监听，焦点消失键盘收起
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                v.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 利用反射获取状态栏高度
     * @return statusBar的height
     */
    public int getStatusBarHeight() {
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return  resources.getDimensionPixelSize(resourceId);
    }
}
