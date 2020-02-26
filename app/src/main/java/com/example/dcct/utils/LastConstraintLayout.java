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
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            int childWithMeasureSpec = getChildMeasureSpec( widthMeasureSpec,this.getPaddingLeft()+this.getPaddingRight() ,lp.width );
            int childHeightMeasureSpec = getChildMeasureSpec( heightMeasureSpec,this.getPaddingBottom() + this.getPaddingTop(), lp.height);
            child.measure(childWithMeasureSpec,childHeightMeasureSpec);
            int heights=child.getMeasuredHeight();
            int widths=child.getMeasuredWidth();
            //这样就得到了child的高度和宽度

            if (lp.width != RelativeLayout.LayoutParams.MATCH_PARENT && lp.width != RelativeLayout.LayoutParams.WRAP_CONTENT && lp.width != 0) {
                lp.width = (int) (lp.width*scaleX);
            }
            if (lp.height != RelativeLayout.LayoutParams.MATCH_PARENT && lp.height != RelativeLayout.LayoutParams.WRAP_CONTENT && lp.height != 0){
                lp.height = (int) (lp.height*scaleY);
            }
            lp.baselineToBaseline = (int) (lp.baselineToBaseline*scaleX);
            lp.bottomToBottom = (int) (lp.bottomToBottom*scaleY);
            lp.leftMargin = (int) (lp.leftMargin*scaleX);
            lp.rightMargin = (int) (lp.rightMargin*scaleX);
            lp.topMargin = (int) (lp.topMargin*scaleY);
            lp.bottomMargin = (int) (lp.bottomMargin*scaleY);
            lp.bottomToTop = (int) (lp.bottomToTop*scaleY);
            lp.bottomToBottom = (int) (lp.bottomToBottom*scaleY);
            lp.endToEnd = (int) (lp.endToEnd*scaleX);
            lp.endToStart = (int) (lp.endToStart*scaleX);
            lp.startToEnd = (int) (lp.startToEnd*scaleX);
            lp.startToStart = (int) (lp.startToStart*scaleX);
            lp.matchConstraintDefaultHeight = (int) (lp.matchConstraintDefaultHeight*scaleY);
            lp.matchConstraintDefaultWidth = (int) (lp.matchConstraintDefaultWidth*scaleX);
            lp.matchConstraintPercentHeight = (int) (lp.matchConstraintPercentHeight*scaleY);
            lp.matchConstraintPercentWidth = (int) (lp.matchConstraintPercentWidth*scaleX);
        }
    }
}
