package com.example.dcct.model;

import com.example.dcct.constants.Constant;
import com.example.dcct.model.internet.BackResultData;
import com.example.dcct.model.internet.CoverEntity;
import com.example.dcct.model.internet.LoginUserEntity;
import com.example.dcct.model.internet.PostQueryEntity;
import com.example.dcct.model.internet.QueryResultEntity;
import com.example.dcct.model.internet.Record;
import com.example.dcct.model.internet.RegisterUserEntity;
import com.example.dcct.model.internet.UserEntity;

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
