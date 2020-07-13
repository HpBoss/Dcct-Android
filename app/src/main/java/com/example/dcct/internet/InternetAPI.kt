package com.example.dcct.internet

import com.example.dcct.bean.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InternetAPI {
    @POST(Constant.LOGIN_API)
    fun submitLoginData(@Body loginUserEntity: LoginUserEntity): Observable<BackResultData<UserEntity>>

    @POST(Constant.REGISTER_API)
    fun submitRegisterData(@Body registerUserEntity: RegisterUserEntity): Observable<BackResultData<*>>

    @GET(Constant.COVER_API)
    fun imageUrl(): Observable<BackResultData<List<CoverEntity>>>

    @POST(Constant.QUERY_API)
    fun submitQueryData(@Body postQueryEntity: PostQueryEntity): Observable<BackResultData<List<QueryResultEntity>>>

    @GET(Constant.RECORD_API)
    fun getRecords(@Path("uid") id: Long): Observable<BackResultData<List<Record>>>

    @POST(Constant.SIGN_OUT)
    fun subSignOutId(@Path("uid") id: Long): Observable<BackResultData<*>>
}