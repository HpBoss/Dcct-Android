package com.example.dcct.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcct.bean.CoverEntity;
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
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_ground, container, false );
        initView( root );
        requestCardInformation();
        return root;
    }

    private void initView(View root) {
        mRecyclerView = root.findViewById( R.id.recycleview );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        mRecyclerView.setLayoutManager( linearLayoutManager );
        /*recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));*///给每一个item添加分割线
//        recyclerView.setNestedScrollingEnabled(false);//取消recyclerView的滑动效果，因为此时它与最外层的scrollView之间存在滑动冲突
    }

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
        mRecyclerView.setAdapter( cardInformationAdapter );
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText( getActivity(),msg,Toast.LENGTH_SHORT ).show();
    }
}