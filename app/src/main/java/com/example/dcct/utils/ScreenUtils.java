package com.example.dcct.utils;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class ScreenUtils {
    //Negotiate with the designer to define a design dimension. Here is 1920*1080 resolution set.
    private static final float widthdp = 360f;
    private static final float heightdp = 640f;

    //Recording system settings
    private static float systemDensity = 0;
    private static float systemScaledDensity = 0;


    public void setCustomDensity(@NonNull final Activity activity) {
        DisplayMetrics displayMetrics = activity.getApplication().getResources().getDisplayMetrics();
        if (systemDensity == 0) {
            //Initialization
            systemDensity = displayMetrics.density;
            systemScaledDensity = displayMetrics.scaledDensity;
            //Add a listener. If the user changes the font of the system, the system will return the listener.
            activity.getApplication().registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        systemScaledDensity = activity.getApplication().getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        //Target value calculation => PX = DP * density
        final float targetDensity = displayMetrics.widthPixels / widthdp;
        final float targetScaledDensity = targetDensity * (systemScaledDensity / systemDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        //Set the calculated value
        displayMetrics.density=targetDensity;
        displayMetrics.scaledDensity=targetScaledDensity;
        displayMetrics.densityDpi=targetDensityDpi;
        //Set the value of activity
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

}
