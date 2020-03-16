package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.presenter.SignOutPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.SignOutCallback;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SignOutPresenterImp implements SignOutPresenter {
    private SignOutCallback mSignOutCallback;
    private static final String TAG = "SignOutPresenterImp";

    @Override
    public void postSignOutId(long uid) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Disposable subscribe = internetApi.subSignOutId( uid )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( backResultData -> {
                    Log.d( TAG, "退出登录 ==》" + backResultData.toString() );
                    if (mSignOutCallback != null) {
                        mSignOutCallback.onLoadSignOutSuccess( backResultData );
                    }
                }, throwable -> Log.d( TAG, Objects.requireNonNull( throwable.getMessage() ) ) );
    }

    @Override
    public void registerCallBack(SignOutCallback signOutCallback) {
        this.mSignOutCallback = signOutCallback;
    }

    @Override
    public void unregisterCallBack(SignOutCallback signOutCallback) {
        mSignOutCallback = null;
    }
}
