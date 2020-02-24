package com.example.dcct.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class LastConstraintLayout extends ConstraintLayout {
    public LastConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LastConstraintLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float scaleX = UIUtils.getInstance(getContext()).getHorValue();
        float scaleY = UIUtils.getInstance(getContext()).getVerValue();
        //获取所有子项view
        int count = this.getChildCount();
        for (int i = 0; i < count ; i++) {
            View child = this.getChildAt(i);
//            int width =View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
//            int height =View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
//            child.measure(width,height);
//            int heights=child.getMeasuredHeight();
//            int widths=child.getMeasuredWidth();
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (layoutParams.width != RelativeLayout.LayoutParams.MATCH_PARENT && layoutParams.width != RelativeLayout.LayoutParams.WRAP_CONTENT && layoutParams.width != 0) {
                layoutParams.width = (int) (layoutParams.width*scaleX);
            }
            if (layoutParams.height != RelativeLayout.LayoutParams.MATCH_PARENT && layoutParams.height != RelativeLayout.LayoutParams.WRAP_CONTENT && layoutParams.height != 0){
                layoutParams.height = (int) (layoutParams.height*scaleY);
            }
            layoutParams.baselineToBaseline = (int) (layoutParams.baselineToBaseline*scaleX);
            layoutParams.bottomToBottom = (int) (layoutParams.bottomToBottom*scaleY);
            layoutParams.leftMargin = (int) (layoutParams.leftMargin*scaleX);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin*scaleX);
            layoutParams.topMargin = (int) (layoutParams.topMargin*scaleY);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin*scaleY);
            layoutParams.bottomToTop = (int) (layoutParams.bottomToTop*scaleY);
            layoutParams.bottomToBottom = (int) (layoutParams.bottomToBottom*scaleY);
            layoutParams.endToEnd = (int) (layoutParams.endToEnd*scaleX);
            layoutParams.endToStart = (int) (layoutParams.endToStart*scaleX);
            layoutParams.startToEnd = (int) (layoutParams.startToEnd*scaleX);
            layoutParams.startToStart = (int) (layoutParams.startToStart*scaleX);
            layoutParams.matchConstraintDefaultHeight = (int) (layoutParams.matchConstraintDefaultHeight*scaleY);
            layoutParams.matchConstraintDefaultWidth = (int) (layoutParams.matchConstraintDefaultWidth*scaleX);
            layoutParams.matchConstraintPercentHeight = (int) (layoutParams.matchConstraintPercentHeight*scaleY);
            layoutParams.matchConstraintPercentWidth = (int) (layoutParams.matchConstraintPercentWidth*scaleX);
        }
    }
}
