package com.example.brandihwtest.repository

import com.example.brandihwtest.repository.response.KakaoImageResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface IRetrofitAPI
{
    //이미지 가져오기
    @GET("/v2/search/image")
    fun getImageLists(  @Query("query") query:String,
                        @Query("sort") sort:String = "accuracy",
                        @Query("page") page:Int,
                        @Query("size") size:Int): Single<KakaoImageResponse>
}