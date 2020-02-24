package com.example.dcct.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.Objects;

public class UIUtils {
    private Context context;

    //需要反射得到状态框的高度
    private static final String DIME_CLASS="com.android.internal.R$dimen";

    //标准值
    private static final  float STANDARD_WIDTH =1080F;
    private static final  float STANDARD_HEIGHT =1920F;

    //实际值（机器的）
    private static float displayMetricsWidth;
    private static float displayMetricsHeight;

    private static UIUtils instance;

    public UIUtils(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0F || displayMetricsHeight == 0.0F) {
            assert windowManager != null;
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int systemBarHeight = getSystemBarHeight(context);
            if (displayMetrics.widthPixels>displayMetrics.heightPixels) {
                //横屏
                displayMetricsWidth = (float) displayMetrics.heightPixels;
                displayMetricsHeight = (float) (displayMetrics.widthPixels-systemBarHeight);
            }else {
                //竖屏
                displayMetricsWidth = (float) displayMetrics.widthPixels;
                displayMetricsHeight = (float) (displayMetrics.heightPixels-systemBarHeight);
            }
        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context,DIME_CLASS,"system_bar_height",48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int i) {
        try {
            Class<?> clz = Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.parseInt(Objects.requireNonNull(field.get(object)).toString());
            return context.getResources().getDimensionPixelOffset(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    //单例
    public static UIUtils getInstance(Context context){
        if (instance == null) {
            instance = new UIUtils(context);
        }
        return instance;
    }

    //缩放比
    public float getHorValue(){
        return ((float) displayMetricsWidth)/ STANDARD_WIDTH;
    }

    public float getVerValue(){
        return ((float) displayMetricsHeight)/(STANDARD_HEIGHT - getSystemBarHeight(context));
    }

}
