package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.model.InternetAPI;
import com.example.dcct.presenter.CoverPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.ImageUrlCallback;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoverPresenterImp implements CoverPresenter {
    private static final String TAG = "CoverPresenterImp";
    private ImageUrlCallback mImageUrlCallback;

    @Override
    public void requestImageUrl() {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Disposable subscribe = internetApi.getImageUrl()
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( listBackResultData -> {
                    Log.d( TAG, "获取图片url ==》" + listBackResultData.toString() );
                    if (mImageUrlCallback != null) {
                        mImageUrlCallback.onLoadImageUrl( listBackResultData.getData() );
                    }
                }, throwable -> Log.d( TAG, Objects.requireNonNull( throwable.getMessage() ) ) );
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
