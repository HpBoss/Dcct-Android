package com.example.dcct.utils;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dcct.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtil {
    private static final int Info = 1;
    public static final int Confirm = 2;
    public static final int Warning = 3;
    public static final int Alert = 4;

    private static int red = 0xfff44336;
    private static int green = 0xff4caf50;
    private static int blue = 0xff2195f3;
    private static int orange = 0xffffc107;

    /**
     * 短显示Snackbar，自定义颜色
     * @param view 控件
     * @param message 信息
     * @param messageColor 文字颜色
     * @param backgroundColor 背景色
     * @return 返回Snackbar
     */
    public static Snackbar ShortSnackbar(View view, String message, int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 长显示Snackbar，自定义颜色
     */
    public static Snackbar LongSnackbar(View view, String message, int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，自定义颜色
     *
     */
    public static Snackbar IndefiniteSnackbar(View view, String message,int duration,int messageColor, int backgroundColor){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackbarColor(snackbar,messageColor,backgroundColor);
        return snackbar;
    }

    /**
     * 短显示Snackbar，可选预设类型
     */
    public static Snackbar ShortSnackbar(View view, String message, int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        switchType(snackbar,type);
        return snackbar;
    }

    /**
     * 长显示Snackbar，可选预设类型
     */
    public static Snackbar LongSnackbar(View view, String message,int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        switchType(snackbar,type);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，可选预设类型
     */
    public static Snackbar IndefiniteSnackbar(View view, String message,int duration,int type){
        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar,type);
        return snackbar;
    }

    //选择预设类型
    private static void switchType(Snackbar snackbar,int type){
        switch (type){
            case Info:
                setSnackbarColor(snackbar,blue);
                break;
            case Confirm:
                setSnackbarColor(snackbar,green);
                break;
            case Warning:
                setSnackbarColor(snackbar,orange);
                break;
            case Alert:
                setSnackbarColor(snackbar, Color.YELLOW,red);
                break;
        }
    }

    /**
     * 设置Snackbar背景颜色
     */
    public static void setSnackbarColor(Snackbar snackbar, int backgroundColor) {
        View view = snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置Snackbar文字和背景颜色
     */
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

    /**
     * 向Snackbar中添加view
     * @param index 新加布局在Snackbar中的位置
     */
    public static void SnackbarAddView( Snackbar snackbar,int layoutId,int index) {
        View snackbarview = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarview;

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId,null);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity= Gravity.CENTER_VERTICAL;

        snackbarLayout.addView(add_view,index,p);
    }

}