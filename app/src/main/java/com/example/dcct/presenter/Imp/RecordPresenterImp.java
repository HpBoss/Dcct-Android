package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.Record;
import com.example.dcct.presenter.RecordPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.RecordCallback;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecordPresenterImp implements RecordPresenter {
    private RecordCallback mRecordCallback;
    private static final String TAG = "RecordPresenterImp";

    @Override
    public void getAllQueryRecord(long id) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Disposable subscribe = internetApi.getRecords( id )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( listBackResultData -> {
                    Log.d( TAG, "个人查询记录 ==》" + listBackResultData.toString() );
                    if (mRecordCallback != null) {
                        mRecordCallback.onLoadQueryData( listBackResultData );
                    }
                }, throwable -> Log.d( TAG, Objects.requireNonNull( throwable.getMessage() ) ) );
    }

    @Override
    public void registerCallBack(RecordCallback recordCallback) {
        this.mRecordCallback = recordCallback;
    }

    @Override
    public void unregisterCallBack(RecordCallback recordCallback) {
        mRecordCallback = null;
    }
}
