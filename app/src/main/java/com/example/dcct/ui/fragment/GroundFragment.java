package com.example.dcct.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcct.bean.CoverEntity;
import com.example.dcct.databinding.FragmentGroundBinding;
import com.example.dcct.model.CoverModel;
import com.example.dcct.model.Impl.CoverModelImp;
import com.example.dcct.presenter.CoverPresenter;
import com.example.dcct.ui.adapter.CardInformationAdapter;
import com.example.dcct.R;
import com.example.dcct.view.ImageUrlCallback;

import java.util.ArrayList;
import java.util.List;

public class GroundFragment extends Fragment implements ImageUrlCallback {

    private List<CoverEntity> cardInformationList = new ArrayList<>();
    private CoverPresenter mCoverPresenter;
    private FragmentGroundBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentGroundBinding.inflate( getLayoutInflater() );
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        //竖直流布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        mBinding.recycleview.setLayoutManager( linearLayoutManager );
        requestCardInformation();
        //给每一个item添加分割线
        /*recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));*/
    }

    /**
     * 请求图片URL
     */
    private void requestCardInformation() {
        mCoverPresenter = new CoverPresenter();
        mCoverPresenter.attachView( this );
        mCoverPresenter.fetchImageUrl();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCoverPresenter != null) {
            mCoverPresenter.detachView();
        }
    }

    @Override
    public void onLoadImageUrl(List<CoverEntity> coverEntities) {
        cardInformationList.addAll( coverEntities );
        CardInformationAdapter cardInformationAdapter = new CardInformationAdapter( cardInformationList, getActivity() );
        mBinding.recycleview.setAdapter( cardInformationAdapter );
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText( getActivity(),msg,Toast.LENGTH_SHORT ).show();
    }
}