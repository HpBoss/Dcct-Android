package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.RegisterUserEntity;
import com.example.dcct.presenter.RegisterPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.RegisterCallback;

import java.net.HttpURLConnection;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RegisterPresenterImp implements RegisterPresenter {

    private static final String TAG = "RegisterPresenterImp";
    private RegisterCallback mCallback = null;

    @Override
    public void postRegisterInfor(RegisterUserEntity registerUserEntity) {
        Retrofit retrofit = new RetrofitManger().getRetrofit();
        API API = retrofit.create(API.class);
        Call<BackResultData> task = API.submitRegisterData( registerUserEntity );
        task.enqueue(new Callback<BackResultData>() {
            @Override
            public void onResponse(Call<BackResultData> call, Response<BackResultData> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData backResultData = response.body();
                    mCallback.onLoadRegisterData( backResultData );
                    Log.d(TAG,"注册 ==》"+ backResultData.toString());
                }
            }

            @Override
            public void onFailure(Call<BackResultData> call, Throwable t) {
                Log.d(TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
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
