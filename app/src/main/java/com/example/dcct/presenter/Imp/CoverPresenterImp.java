package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.CoverEntity;
import com.example.dcct.presenter.CoverPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.ImageUrlCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CoverPresenterImp implements CoverPresenter {

    private ImageUrlCallback imageUrlCallback;

    @Override
    public void requestImageUrl() {
        Retrofit retrofit = RetrofitManger.getInstance().getRetrofit();
        API api = retrofit.create( API.class );
        Call<BackResultData<List<CoverEntity>>> task = api.getImageUrl();
        task.enqueue( new Callback<BackResultData<List<CoverEntity>>>() {
            @Override
            public void onResponse(Call<BackResultData<List<CoverEntity>>> call, Response<BackResultData<List<CoverEntity>>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData<List<CoverEntity>> backResultData = response.body();
                    if (backResultData != null) {
                        List<CoverEntity> coverEntities = backResultData.getData();
//                        for (CoverEntity s : coverEntities) {
//                            Log.d( "CoverPresenterImp",s.toString() );
//                        }
                        if (imageUrlCallback != null) {
                            imageUrlCallback.onLoadImageUrl( coverEntities );
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BackResultData<List<CoverEntity>>> call, Throwable t) {
                Log.d( "CoverPresenterImp" ,"广场封面图获取失败图片");
            }
        } );
    }

    @Override
    public void registerCallBack(ImageUrlCallback imageUrlCallback) {
        this.imageUrlCallback = imageUrlCallback;
    }

    @Override
    public void unregisterCallBack(ImageUrlCallback imageUrlCallback) {

    }
}
