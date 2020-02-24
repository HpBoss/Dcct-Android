package com.example.dcct.presenter.Imp;

import android.widget.Toast;

import com.example.dcct.model.API;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.Record;
import com.example.dcct.presenter.RecordPresenter;
import com.example.dcct.utils.RetrofitManger;
import com.example.dcct.view.RecordCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecordPresenterImp implements RecordPresenter {
    private RecordCallback mRecordCallback;

    @Override
    public void getAllQueryRecord(long id) {
        Retrofit retrofit = RetrofitManger.getInstance().getRetrofit();
        API api = retrofit.create( API.class );
        Call<BackResultData<List<Record>>> task = api.getRecords( id );
        task.enqueue( new Callback<BackResultData<List<Record>>>() {
            @Override
            public void onResponse(Call<BackResultData<List<Record>>> call, Response<BackResultData<List<Record>>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    BackResultData<List<Record>> backResultData = response.body();
                    if (mRecordCallback != null) {
                        mRecordCallback.onLoadQueryData( backResultData );
                    }
                }
            }

            @Override
            public void onFailure(Call<BackResultData<List<Record>>> call, Throwable t) {
                if (mRecordCallback != null){
                    mRecordCallback.onLoadError( t );
                }
            }
        } );
    }

    @Override
    public void registerCallBack(RecordCallback recordCallback) {
        this.mRecordCallback = recordCallback;
    }

    @Override
    public void unregisterCallBack(RecordCallback recordCallback) {
        recordCallback = null;
    }
}
