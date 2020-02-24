package com.example.dcct.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.PostQueryEntity;
import com.example.dcct.model.internet.model.QueryResultEntity;
import com.example.dcct.model.internet.model.ReportSerializable;
import com.example.dcct.presenter.Imp.QueryPresenterImp;
import com.example.dcct.presenter.QueryPresenter;
import com.example.dcct.ui.adapter.GaugingPageAdapter;
import com.example.dcct.ui.activity.GaugingReportActivity;
import com.example.dcct.R;
import com.example.dcct.view.QueryCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GaugingFragment extends Fragment implements QueryCallback {


    private ViewPager mViewPager;
    private MAndMFragment mMAndMFragment;
    private MAndFoodFragment mMAndFoodFragment;
    private transmitFragment mTransmitFragment;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Button mGaugingButton;
    private QueryPresenter mQueryPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gauging, container, false);
        addFragment();
        initView(root);
        mTransmitFragment.setTwoFragment(mMAndMFragment,mMAndFoodFragment);
        return root;
    }

    private void addFragment() {
        mMAndMFragment = new MAndMFragment();
        mMAndFoodFragment = new MAndFoodFragment();
        mFragmentList.add(mMAndMFragment);
        mFragmentList.add(mMAndFoodFragment);
    }

    private void initView(View root) {
        TabLayout tabLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.tabSegment);
        mViewPager = root.findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        final GaugingPageAdapter gaugingPageAdapter = new GaugingPageAdapter(getChildFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mFragmentList);
        mViewPager.setAdapter(gaugingPageAdapter);
        mGaugingButton = root.findViewById(R.id.gaugingButton);
        mGaugingButton.setOnClickListener( v -> {
            List<String> textData;
            SharedPreferences preferences = getActivity().getSharedPreferences( "SHARE_APP_DATA",Context.MODE_PRIVATE );
            long uid = preferences.getLong( "uid", 1 );

            if (mViewPager.getCurrentItem() == 0) {
                if (!mMAndMFragment.doCheckCompleteAll()) {
                    displayWarning();
                }else {
                    textData = mMAndMFragment.backTextData();
                    mQueryPresenter = new QueryPresenterImp();
                    mQueryPresenter.registerCallBack( this );
                    PostQueryEntity postQueryEntity = new PostQueryEntity(textData.get( 0 ),textData.get( 1 ),uid);
                    mQueryPresenter.postQueryInformation( postQueryEntity );
                    mMAndMFragment.clearEditeContent();
                }
            }else {
                if (!mMAndFoodFragment.doCheckCompleteAll()){
                    displayWarning();
                }else {
                    textData = mMAndFoodFragment.backTextData();
                    mQueryPresenter = new QueryPresenterImp();
                    mQueryPresenter.registerCallBack( this );
                    PostQueryEntity postQueryEntity = new PostQueryEntity(textData.get( 0 ),textData.get( 1 ),uid);
                    mQueryPresenter.postQueryInformation( postQueryEntity );
                    mMAndFoodFragment.clearEditeContent();
                }
            }
        } );
    }

    private void jumpToReportActivity(List<QueryResultEntity> queryResultEntityList) {
        ReportSerializable reportSerializable = new ReportSerializable(queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugOne(),
                queryResultEntityList.get( 1 ).getDrugNameEntity().getDrugOne(),
                queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugTwo(),queryResultEntityList.get( 1 ).getDrugNameEntity().getDrugTwo(),
                queryResultEntityList.get( 0 ).getResult(),queryResultEntityList.get( 1 ).getResult(),
                queryResultEntityList.get( 0 ).getScore(),queryResultEntityList.get( 1 ).getScore());
        Intent intent = new Intent(getActivity(), GaugingReportActivity.class);
        intent.putExtra( "queryData",reportSerializable);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mQueryPresenter != null) {
            mQueryPresenter.unregisterCallBack( this );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GaugingFragment.transmitFragment) {
            mTransmitFragment = (GaugingFragment.transmitFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement transmitFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTransmitFragment = null;
    }

    @Override
    public void onLoadQueryData(BackResultData<List<QueryResultEntity>> backResultData) {
        if (backResultData.isState()) {
            List<QueryResultEntity> queryResultEntityList = backResultData.getData();
            jumpToReportActivity( queryResultEntityList );
        }else {
            Toast.makeText( getActivity(),backResultData.getMsg(),Toast.LENGTH_LONG ).show();
        }

    }

    public interface transmitFragment{
        void setTwoFragment(MAndMFragment fragment1,MAndFoodFragment fragment2);
    }

    public void displayWarning(){
        new AlertDialog.Builder(getActivity())
                .setMessage("信息填写不完整！！！")
                .setCancelable(false)
                .setPositiveButton("OK", null)
                .show();
    }

    public void rotateAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_360_degress);
        /*LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);设置运行速度*/
        /*LinearInterpolator为匀速效果，AccelerateInterpolator为加速效果、DecelerateInterpolator为减速效果*/
        mGaugingButton.startAnimation(animation);
        /*mGaugingButton.clearAnimation();关闭动画*/
    }
}