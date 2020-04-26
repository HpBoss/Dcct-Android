package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.RegisterUserEntity;
import com.example.dcct.model.RegisterModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.RegisterCallback;

import java.util.Objects;

import io.reactivex.Observable;


public class RegisterModelImp implements RegisterModel {
    private static final String TAG = "RegisterPresenterImp";

    @Override
    public void postRegisterInfor(RegisterUserEntity registerUserEntity,ReturnRegisterResult returnRegisterResult) {
        InternetAPI internetAPI = NetWorkApi.getInstance().getService();
        Observable<BackResultData> compose = internetAPI.submitRegisterData( registerUserEntity )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData>() {
                    @Override
                    public void onSuccess(BackResultData backResultData) {
//                        Log.d( TAG, "注册 ==》" + backResultData.toString() );
                        returnRegisterResult.onComplete( backResultData );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnRegisterResult.onError( e.getMessage() );
                    }
                } ) );
    }

}
