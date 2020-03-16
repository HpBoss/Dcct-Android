package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.model.InternetAPI;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.LoginUserEntity;
import com.example.dcct.model.internet.UserEntity;
import com.example.dcct.presenter.LoginPresenter;
import com.example.dcct.constants.NetWorkApi;
import com.example.dcct.view.LoginCallback;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {
    private static final String TAG = "LoginPresenterImp";
    private LoginCallback mCallback = null;

    @Override
    public void postLoginData(LoginUserEntity loginUserEntity) {
        InternetAPI internetAPI = NetWorkApi.getInstance().getService();
        internetAPI.submitLoginData( loginUserEntity )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new BaseObserver<BackResultData<UserEntity>>() {
                    @Override
                    public void onSuccess(BackResultData<UserEntity> userEntityBackResultData) {
                        Log.d(TAG, "登录 ==》"+ userEntityBackResultData.toString());
                        if (mCallback != null) {
                            mCallback.onLoadLoginData( userEntityBackResultData );
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.d(TAG, "登录 ==》"+ Objects.requireNonNull(e.getMessage()));
                    }
                } );
    }

    @Override
    public void registerCallBack(LoginCallback loginCallBack) {
        this.mCallback = loginCallBack;
    }

    @Override
    public void unregisterCallBack(LoginCallback loginCallBack) {
        mCallback = null;
    }


}
