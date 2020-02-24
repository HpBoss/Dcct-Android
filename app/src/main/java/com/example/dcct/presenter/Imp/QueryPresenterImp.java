package com.example.dcct.presenter.Imp;

import android.util.Log;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.PostQueryEntity;
import com.example.dcct.model.internet.model.QueryResultEntity;
import com.example.dcct.presenter.QueryPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.QueryCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QueryPresenterImp implements QueryPresenter {
    private QueryCallback mQueryCallback;

    @Override
    public void postQueryInformation(PostQueryEntity postQueryEntity) {
        Retrofit retrofit = RetrofitManger.getInstance().getRetrofit();
        API api = retrofit.create( API.class );
        Call<BackResultData<List<QueryResultEntity>>> task = api.submitQueryData( postQueryEntity );
        task.enqueue( new Callback<BackResultData<List<QueryResultEntity>>>() {
            @Override
            public void onResponse(Call<BackResultData<List<QueryResultEntity>>> call, Response<BackResultData<List<QueryResultEntity>>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData<List<QueryResultEntity>> backResultData = response.body();
                    if (backResultData != null && mQueryCallback != null) {
                        mQueryCallback.onLoadQueryData( backResultData );
                    }
                }
            }

            @Override
            public void onFailure(Call<BackResultData<List<QueryResultEntity>>> call, Throwable t) {

            }
        } );
    }

    @Override
    public void registerCallBack(QueryCallback queryCallback) {
        this.mQueryCallback = queryCallback;
    }

    @Override
    public void unregisterCallBack(QueryCallback queryCallback) {
        mQueryCallback = null;
    }
}
