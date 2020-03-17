package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.model.QueryModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.QueryCallback;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;

public class QueryModelImp implements QueryModel {
    private static final String TAG = "QueryPresenterImp";

    @Override
    public void postQueryInformation(PostQueryEntity postQueryEntity,ReturnQueryResult returnQueryResult) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Observable<BackResultData<List<QueryResultEntity>>> compose = internetApi.submitQueryData( postQueryEntity )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<List<QueryResultEntity>>>() {
                    @Override
                    public void onSuccess(BackResultData<List<QueryResultEntity>> listBackResultData) {
                        Log.d( TAG, "查询结果 ==》" + listBackResultData.toString() );
                        returnQueryResult.onComplete(listBackResultData);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnQueryResult.onError( e.getMessage() );
                    }
                } ) );
    }

}
