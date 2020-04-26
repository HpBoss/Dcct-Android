package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.CoverEntity;
import com.example.dcct.model.CoverModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.ImageUrlCallback;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;

public class CoverModelImp implements CoverModel {
    private static final String TAG = "CoverPresenterImp";

    @Override
    public void requestImageUrl(OnReturnListener onReturnListener) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Observable<BackResultData<List<CoverEntity>>> compose = internetApi.getImageUrl()
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<List<CoverEntity>>>() {
                    @Override
                    public void onSuccess(BackResultData<List<CoverEntity>> listBackResultData) {
//                        Log.d( TAG, "获取图片url ==》" + listBackResultData.toString() );
                        onReturnListener.onComplete( listBackResultData.getData() );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        onReturnListener.onError( e.getMessage() );
                    }
                } ) );
    }


}
