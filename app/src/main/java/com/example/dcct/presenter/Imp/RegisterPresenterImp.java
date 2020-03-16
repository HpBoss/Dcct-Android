package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.RegisterUserEntity;
import com.example.dcct.presenter.RegisterPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.RegisterCallback;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RegisterPresenterImp implements RegisterPresenter {

    private static final String TAG = "RegisterPresenterImp";
    private RegisterCallback mCallback = null;

    @Override
    public void postRegisterInfor(RegisterUserEntity registerUserEntity) {
        InternetAPI internetAPI = NetWorkApi.getInstance().getService();
        Observable<BackResultData> compose = internetAPI.submitRegisterData( registerUserEntity )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData>() {
                    @Override
                    public void onSuccess(BackResultData backResultData) {
                        Log.d( TAG, "注册 ==》" + backResultData.toString() );
                        if (mCallback != null) {
                            mCallback.onLoadRegisterData( backResultData );
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.d( TAG, Objects.requireNonNull( e.getMessage() ) );
                    }
                } ) );
    }

    @Override
    public void registerCallBack(RegisterCallback registerCallback) {
        mCallback = registerCallback;
    }

    @Override
    public void unregisterCallBack(RegisterCallback registerCallback) {
        mCallback = null;
    }
}
