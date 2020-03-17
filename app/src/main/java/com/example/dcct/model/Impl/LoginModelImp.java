package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.model.LoginModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.LoginCallback;

import java.util.Objects;

import io.reactivex.Observable;

public class LoginModelImp implements LoginModel {
    private static final String TAG = "LoginPresenterImp";

    @Override
    public void postLoginData(LoginUserEntity loginUserEntity,ReturnLoginData returnLoginData) {
        InternetAPI internetAPI = NetWorkApi.getInstance().getService();
        Observable<BackResultData<UserEntity>> compose = internetAPI.submitLoginData( loginUserEntity )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<UserEntity>>() {
                    @Override
                    public void onSuccess(BackResultData<UserEntity> userEntityBackResultData) {
                        Log.d( TAG, "登录 ==》" + userEntityBackResultData.toString() );
                            returnLoginData.onComplete( userEntityBackResultData );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnLoginData.onError( e.getMessage() );
                    }
                } ) );
    }

}
