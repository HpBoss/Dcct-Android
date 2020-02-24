package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.LoginUserEntity;
import com.example.dcct.model.internet.model.UserEntity;
import com.example.dcct.presenter.LoginPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.LoginCallback;

import java.net.HttpURLConnection;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenterImp implements LoginPresenter {
    private static final String TAG = "LoginPresenterImp";
    private LoginCallback mCallback = null;

    @Override
    public void postLoginData(LoginUserEntity loginUserEntity) {
        Retrofit retrofit = new RetrofitManger().getRetrofit();
        API API = retrofit.create(API.class);
        Call<BackResultData<UserEntity>> task = API.submitLoginData( loginUserEntity );
        task.enqueue(new Callback<BackResultData<UserEntity>>() {
            @Override
            public void onResponse(Call<BackResultData<UserEntity>> call, Response<BackResultData<UserEntity>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData<UserEntity> backResultData = response.body();
                    Log.d(TAG, "登录 ==》"+ backResultData.toString());
                    if (mCallback != null) {
                        mCallback.onLoadLoginData( backResultData );
                    }
                }
            }

            @Override
            public void onFailure(Call<BackResultData<UserEntity>> call, Throwable t) {
                Log.d(TAG, "登录 ==》"+ Objects.requireNonNull(t.getMessage()));
            }
        });
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
