package com.example.dcct.presenter.Imp;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.presenter.SignOutPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.SignOutCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignOutPresenterImp implements SignOutPresenter {
    private SignOutCallback mSignOutCallback;

    @Override
    public void postSignOutId(long uid) {
        Retrofit retrofit = RetrofitManger.getInstance().getRetrofit();
        API api = retrofit.create( API.class );
        Call<BackResultData> task = api.subSignOutId( uid );
        task.enqueue( new Callback<BackResultData>() {
            @Override
            public void onResponse(Call<BackResultData> call, Response<BackResultData> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData backResultData = response.body();
                    if (mSignOutCallback != null) {
                        mSignOutCallback.onLoadSignOutSuccess( backResultData );
                    }
                }
            }

            @Override
            public void onFailure(Call<BackResultData> call, Throwable t) {

            }
        } );
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
