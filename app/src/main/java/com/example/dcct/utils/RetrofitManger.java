package com.example.dcct.utils;

import com.example.dcct.constants.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManger {
    private static final RetrofitManger ourInstance = new RetrofitManger();
    private Retrofit mRetrofit;

    public static RetrofitManger getInstance() {
        return ourInstance;
    }

    public RetrofitManger() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
