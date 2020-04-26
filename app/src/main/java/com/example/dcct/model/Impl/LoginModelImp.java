package com.example.dcct.model.Impl;

import android.util.Log;

import com.example.dcct.base.BaseObserver;
import com.example.dcct.bean.TokenEntity;
import com.example.dcct.internet.InternetAPI;
import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.model.LoginModel;
import com.example.dcct.internet.NetWorkApi;
import com.example.dcct.view.LoginCallback;

import java.util.Objects;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModelImp implements LoginModel {
    private static final String TAG = "LoginPresenterImp";

    @Override
    public void postLoginData(LoginUserEntity loginUserEntity,ReturnLoginData returnLoginData) {
        InternetAPI internetAPI = NetWorkApi.getInstance().getService();
        //ResponseBody
        Observable<BackResultData<TokenEntity>> task = internetAPI.submitLoginData( loginUserEntity )
                .compose( NetWorkApi.applySchedulers( new BaseObserver<BackResultData<TokenEntity>>() {
                    @Override
                    public void onSuccess(BackResultData<TokenEntity> userEntityBackResultData) {
//                        Log.d( TAG, "登录 ==》" + userEntityBackResultData.toString() );
                            returnLoginData.onComplete( userEntityBackResultData );
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        returnLoginData.onError( e.getMessage() );
                    }
                } ) );
    }

}
