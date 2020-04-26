package com.example.dcct.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.bean.ReportParcelable;
import com.example.dcct.databinding.FragmentGaugingBinding;
import com.example.dcct.presenter.QueryPresenter;
import com.example.dcct.ui.adapter.GaugingPageAdapter;
import com.example.dcct.ui.activity.GaugingReportActivity;
import com.example.dcct.R;
import com.example.dcct.view.QueryCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GaugingFragment extends Fragment implements QueryCallback {

    private MAndMFragment mMAndMFragment;
    private MAndFoodFragment mMAndFoodFragment;
    private transmitFragment mTransmitFragment;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private QueryPresenter mQueryPresenter;
    private FragmentGaugingBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBinding = FragmentGaugingBinding.inflate( getLayoutInflater() );
        addFragment();
        mTransmitFragment.setTwoFragment( mMAndMFragment, mMAndFoodFragment );
        return mBinding.getRoot();
    }

    private void addFragment() {
        mMAndMFragment = new MAndMFragment();
        mMAndFoodFragment = new MAndFoodFragment();
        mFragmentList.add( mMAndMFragment );
        mFragmentList.add( mMAndFoodFragment );
        //添加过渡动画，测试


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        TabLayout tabSegment = Objects.requireNonNull( getActivity() ).findViewById( R.id.tabSegment );
        tabSegment.setupWithViewPager( mBinding.viewPager );

        mBinding.viewPager.setCurrentItem( 0 );
        final GaugingPageAdapter gaugingPageAdapter = new GaugingPageAdapter( getChildFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragmentList );
        mBinding.viewPager.setAdapter( gaugingPageAdapter );
        mBinding.gaugingButton.setOnClickListener( v -> {
            List<String> textData;
            SharedPreferences preferences = Objects.requireNonNull( getActivity() )
                    .getSharedPreferences( "SHARE_APP_DATA", Context.MODE_PRIVATE );
            long uid = preferences.getLong( "uid", 1 );
            mQueryPresenter = new QueryPresenter();
            mQueryPresenter.attachView( this );

            if (mBinding.viewPager.getCurrentItem() == 0) {
                if (!mMAndMFragment.doCheckCompleteAll()) {
                    displayAlertDialog();
                } else {
                    textData = mMAndMFragment.backTextData();
                    PostQueryEntity postQueryEntity = new PostQueryEntity( textData.get( 0 ), textData.get( 1 ), uid );
                    mQueryPresenter.fetchQueryResult( postQueryEntity );
                    mMAndMFragment.clearEditContent();
                }
            } else {
                if (!mMAndFoodFragment.doCheckCompleteAll()) {
                    displayAlertDialog();
                } else {
                    textData = mMAndFoodFragment.backTextData();
                    PostQueryEntity postQueryEntity = new PostQueryEntity( textData.get( 0 ), textData.get( 1 ), uid );
                    mQueryPresenter.fetchQueryResult( postQueryEntity );
                    mMAndFoodFragment.clearEditContent();
                }
            }
        } );
    }

    private void jumpToReportActivity(List<QueryResultEntity> queryResultEntityList) {
        ReportParcelable reportParcelable = new ReportParcelable( queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugOne(),
                queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugTwo(),
                queryResultEntityList.get( 0 ).getResult(), queryResultEntityList.get( 1 ).getResult(),
                queryResultEntityList.get( 0 ).getScore(), queryResultEntityList.get( 1 ).getScore() );
        Intent intent = new Intent( getActivity(), GaugingReportActivity.class );
        intent.putExtra( "queryData", reportParcelable );
        startActivity( intent );
        Objects.requireNonNull( getActivity() ).overridePendingTransition( R.anim.translate_right_in, R.anim.translate_left_out );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mQueryPresenter != null) {
            mQueryPresenter.detachView();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof GaugingFragment.transmitFragment) {
            mTransmitFragment = (GaugingFragment.transmitFragment) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement transmitFragment" );
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
            Toast.makeText( getActivity(), backResultData.getMsg(), Toast.LENGTH_LONG ).show();
            jumpToReportActivity( queryResultEntityList );
        } else {
            Toast.makeText( getActivity(), backResultData.getMsg(), Toast.LENGTH_LONG ).show();
        }

    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText( getActivity(),msg,Toast.LENGTH_SHORT ).show();
    }

    public interface transmitFragment {
        void setTwoFragment(MAndMFragment fragment1, MAndFoodFragment fragment2);
    }

    private void displayAlertDialog() {
        new SweetAlertDialog( Objects.requireNonNull( getActivity() ), SweetAlertDialog.WARNING_TYPE )
                .setTitleText( "查询信息未填写完整" )
                .setConfirmText( "确定" )
                .setConfirmClickListener( SweetAlertDialog::dismissWithAnimation )
                .show();
    }

    public void rotateAnimation() {
        Animation animation = AnimationUtils.loadAnimation( getActivity(), R.anim.rotate_360_degress );
        /*LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);设置运行速度*/
        /*LinearInterpolator为匀速效果，AccelerateInterpolator为加速效果、DecelerateInterpolator为减速效果*/
        mBinding.gaugingButton.startAnimation( animation );
        /*mGaugingButton.clearAnimation();关闭动画*/
    }
}