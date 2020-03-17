package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.Record;
import com.example.dcct.model.RecordModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.RecordCallback;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;

public class RecordModelImp implements RecordModel {
    private static final String TAG = "RecordPresenterImp";

    @Override
    public void getAllQueryRecord(long id,ReturnQueryRecord returnQueryRecord) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Observable<BackResultData<List<Record>>> compose = internetApi.getRecords( id )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<List<Record>>>() {
                    @Override
                    public void onSuccess(BackResultData<List<Record>> listBackResultData) {
                        Log.d( TAG, "个人查询记录 ==》" + listBackResultData.toString() );
                        returnQueryRecord.onComplete( listBackResultData );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnQueryRecord.onError( e.getMessage() );
                    }
                } ) );
    }
}
