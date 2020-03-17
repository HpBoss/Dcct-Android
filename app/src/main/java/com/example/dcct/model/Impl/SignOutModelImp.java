package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.model.SignOutModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.SignOutCallback;

import java.util.Objects;

import io.reactivex.Observable;

public class SignOutModelImp implements SignOutModel {
    private static final String TAG = "SignOutPresenterImp";

    @Override
    public void postSignOutId(long uid,ReturnSignState returnSignState) {
        InternetAPI internetApi = NetWorkApi.getInstance().getService();
        Observable<BackResultData> compose = internetApi.subSignOutId( uid )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData>() {
                    @Override
                    public void onSuccess(BackResultData backResultData) {
                        Log.d( TAG, "退出登录 ==》" + backResultData.toString() );
                        returnSignState.onComplete( backResultData );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnSignState.onError( e.getMessage() );
                    }
                } ) );
    }
}
