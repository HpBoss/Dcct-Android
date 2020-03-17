package com.example.dcct.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.bean.Record;
import com.example.dcct.bean.ReportParcelable;
import com.example.dcct.presenter.QueryPresenter;
import com.example.dcct.presenter.RecordPresenter;
import com.example.dcct.ui.activity.GaugingReportActivity;
import com.example.dcct.R;
import com.example.dcct.ui.adapter.RecordHistoryAdapter;
import com.example.dcct.view.QueryCallback;
import com.example.dcct.view.RecordCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecordFragment extends Fragment implements RecordCallback {

    private List<Record> mRecordList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private long mUid;
    private QueryPresenter mQueryPresenter;
    private RecordPresenter mRecordPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_record, container, false );
        initView( view );
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById( R.id.recordRecycleView );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        mRecyclerView.setLayoutManager( linearLayoutManager );

        SharedPreferences preferences = Objects.requireNonNull( getActivity() ).getSharedPreferences( "SHARE_APP_DATA", Context.MODE_PRIVATE );
        mUid = preferences.getLong( "uid", 1 );
        mRecordPresenter = new RecordPresenter();
        mRecordPresenter.attachView( this );
        mRecordPresenter.fetchQueryRecord( mUid );
    }

    @Override
    public void onLoadQueryData(BackResultData<List<Record>> backResultData) {
        if (backResultData.isState()) {
            mRecordList = backResultData.getData();
            RecordHistoryAdapter recordHistoryAdapter = new RecordHistoryAdapter( mRecordList );
            mRecyclerView.setAdapter( recordHistoryAdapter );
            recordHistoryAdapter.setOnClickItems( position -> {
                String queryName = mRecordList.get( position ).getQueryName();
                String drugOne = queryName.substring( 0, queryName.lastIndexOf( "、" ) );
                String drugTwo = queryName.substring( queryName.lastIndexOf( "、" ) + 1 );

                PostQueryEntity postQueryEntity = new PostQueryEntity( drugOne, drugTwo, mUid );
                mQueryPresenter = new QueryPresenter();
                mQueryPresenter.attachView( mQueryCallback );
                mQueryPresenter.fetchQueryResult( postQueryEntity );

            } );
        }
    }

    @Override
    public void onLoadError(Throwable t) {
        Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_LONG ).show();
    }

    private QueryCallback mQueryCallback = new QueryCallback() {
        @Override
        public void onLoadQueryData(BackResultData<List<QueryResultEntity>> backResultData) {
            if (backResultData.isState()) {
                List<QueryResultEntity> queryResultEntityList = backResultData.getData();
                ReportParcelable reportParcelable = new ReportParcelable( queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugOne(),
                        queryResultEntityList.get( 0 ).getDrugNameEntity().getDrugTwo(),
                        queryResultEntityList.get( 0 ).getResult(), queryResultEntityList.get( 1 ).getResult(),
                        queryResultEntityList.get( 0 ).getScore(), queryResultEntityList.get( 1 ).getScore() );
                Intent intent = new Intent( getActivity(), GaugingReportActivity.class );
                intent.putExtra( "queryData", reportParcelable );
                startActivity( intent );
                Objects.requireNonNull( getActivity() ).overridePendingTransition( R.anim.translate_right_in, R.anim.translate_left_out );
            } else {
                Toast.makeText( getActivity(), backResultData.getMsg(), Toast.LENGTH_LONG ).show();
            }
        }

        @Override
        public void showErrorMsg(String msg) {
            Toast.makeText( getActivity(),msg,Toast.LENGTH_SHORT ).show();
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRecordPresenter != null) {
            mRecordPresenter.detachView();
        }
        if (mQueryPresenter != null) {
            mQueryPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String msg) {

    }
}