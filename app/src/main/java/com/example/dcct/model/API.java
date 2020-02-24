package com.example.dcct.model;

import com.example.dcct.constants.Constant;
import com.example.dcct.model.internet.model.BackResultData;
import com.example.dcct.model.internet.model.CoverEntity;
import com.example.dcct.model.internet.model.LoginUserEntity;
import com.example.dcct.model.internet.model.PostQueryEntity;
import com.example.dcct.model.internet.model.QueryResultEntity;
import com.example.dcct.model.internet.model.Record;
import com.example.dcct.model.internet.model.RegisterUserEntity;
import com.example.dcct.model.internet.model.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @POST(Constant.LOGIN_API)
    Call<BackResultData<UserEntity>> submitLoginData(@Body LoginUserEntity loginUserEntity);

    @POST(Constant.REGISTER_API)
    Call<BackResultData> submitRegisterData(@Body RegisterUserEntity registerUserEntity);

    @GET(Constant.COVER_API)
    Call<BackResultData<List<CoverEntity>>> getImageUrl();

    @POST(Constant.QUERY_API)
    Call<BackResultData<List<QueryResultEntity>>> submitQueryData(@Body PostQueryEntity postQueryEntity);

    @GET(Constant.RECORD_API)
    Call<BackResultData<List<Record>>> getRecords(@Path("uid") long id);

    @POST(Constant.SIGN_OUT)
    Call<BackResultData> subSignOutId(@Path( "uid" ) long id);
}
