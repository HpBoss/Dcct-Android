package com.example.dcct.internet;

import com.example.dcct.bean.BackResultData;
import com.example.dcct.bean.CoverEntity;
import com.example.dcct.bean.LoginUserEntity;
import com.example.dcct.bean.PostQueryEntity;
import com.example.dcct.bean.QueryResultEntity;
import com.example.dcct.bean.Record;
import com.example.dcct.bean.RegisterUserEntity;
import com.example.dcct.bean.UserEntity;
import com.example.dcct.internet.Constant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InternetAPI {

    @POST(Constant.LOGIN_API)
    Observable<BackResultData<UserEntity>> submitLoginData(@Body LoginUserEntity loginUserEntity);

    @POST(Constant.REGISTER_API)
    Observable<BackResultData> submitRegisterData(@Body RegisterUserEntity registerUserEntity);

    @GET(Constant.COVER_API)
    Observable<BackResultData<List<CoverEntity>>> getImageUrl();

    @POST(Constant.QUERY_API)
    Observable<BackResultData<List<QueryResultEntity>>> submitQueryData(@Body PostQueryEntity postQueryEntity);

    @GET(Constant.RECORD_API)
    Observable<BackResultData<List<Record>>> getRecords(@Path("uid") long id);

    @POST(Constant.SIGN_OUT)
    Observable<BackResultData> subSignOutId(@Path( "uid" ) long id);
}
