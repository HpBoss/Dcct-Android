package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.PostQueryEntity;
import com.example.dcct.model.internet.QueryResultEntity;
import com.example.dcct.presenter.QueryPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.QueryCallback;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QueryPresenterImp implements QueryPresenter {
    private QueryCallback mQueryCallback;
    private static final String TAG = "QueryPresenterImp";

    @Override
    public void postQueryInformation(PostQueryEntity postQueryEntity) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Disposable subscribe = internetApi.submitQueryData( postQueryEntity )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( listBackResultData -> {
                    Log.d( TAG, "查询结果 ==》" + listBackResultData.toString() );
                    if (mQueryCallback != null) {
                        mQueryCallback.onLoadQueryData( listBackResultData );
                    }
                }, throwable -> Log.d( TAG, Objects.requireNonNull( throwable.getMessage() ) ) );
    }

    @Override
    public void registerCallBack(QueryCallback queryCallback) {
        this.mQueryCallback = queryCallback;
    }

    @Override
    public void unregisterCallBack(QueryCallback queryCallback) {
        mQueryCallback = null;
    }
}
