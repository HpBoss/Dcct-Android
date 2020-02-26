package com.example.dcct.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.dcct.R;
import com.example.dcct.base.BaseActivity;
import com.example.dcct.databinding.ActivityGuideBinding;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private int mCurrentItem = 0;
    private List<Integer> guideList = new ArrayList<>();
    private ActivityGuideBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityGuideBinding.inflate( LayoutInflater.from( this ) );
        setContentView(mBinding.getRoot());

        mBinding.comeIndex.setOnClickListener(this);
        getData();
        addGuidePicture();
        mBinding.indicate.getChildAt(0).setEnabled(true);
        mBinding.viewPager.addOnPageChangeListener(this);
        mBinding.viewPager.setAdapter( new PagerAdapter() {
            @Override
            public int getCount() {
                return guideList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView iv = new ImageView(container.getContext());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(guideList.get(position));
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }

    private void addGuidePicture() {
        guideList.add(R.drawable.guide1);
        guideList.add(R.drawable.guide2);
        guideList.add(R.drawable.guide3);
    }


    private void getData() {
        View view;
        for (int i = 0; i < 3; i++) {
            //创建底部指示器(小圆点)
            view = new View(this);
            view.setBackgroundResource(R.drawable.indicator);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
            if (i != 0) {
                layoutParams.leftMargin = 30;
            }
            //添加到LinearLayout
            mBinding.indicate.addView(view, layoutParams);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //当滑动至最后一页时，隐藏指示器，显示按钮
        if (position==2){
            mBinding.indicate.setVisibility(View.GONE);
            mBinding.comeIndex.setVisibility(View.VISIBLE);
        }else {
            mBinding.indicate.setVisibility(View.VISIBLE);
            mBinding.comeIndex.setVisibility(View.GONE);
        }
        //指示器变化效果
        mBinding.indicate.getChildAt(mCurrentItem).setEnabled(false);
        mBinding.indicate.getChildAt(position).setEnabled(true);
        mCurrentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if (isLoginSuccess()){
            activityJump(GuideActivity.this, MainActivity.class);
            translatingAnimation_rightToLeft();
        }else {
            activityJump(GuideActivity.this, LoginAndRegisterActivity.class);
            translatingAnimation_rightToLeft();
        }
    }
}
