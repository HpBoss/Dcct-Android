package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.CoverEntity;
import com.example.dcct.presenter.CoverPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.ImageUrlCallback;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoverPresenterImp implements CoverPresenter {
    private static final String TAG = "CoverPresenterImp";
    private ImageUrlCallback mImageUrlCallback;

    @Override
    public void requestImageUrl() {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Observable<BackResultData<List<CoverEntity>>> compose = internetApi.getImageUrl()
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<List<CoverEntity>>>() {
                    @Override
                    public void onSuccess(BackResultData<List<CoverEntity>> listBackResultData) {
                        Log.d( TAG, "获取图片url ==》" + listBackResultData.toString() );
                        if (mImageUrlCallback != null) {
                            mImageUrlCallback.onLoadImageUrl( listBackResultData.getData() );
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.d( TAG, Objects.requireNonNull( e.getMessage() ) );
                    }
                } ) );
    }

    @Override
    public void registerCallBack(ImageUrlCallback imageUrlCallback) {
        this.mImageUrlCallback = imageUrlCallback;
    }

    @Override
    public void unregisterCallBack(ImageUrlCallback imageUrlCallback) {
        mImageUrlCallback = null;
    }
}
