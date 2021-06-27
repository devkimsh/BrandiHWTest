package com.example.brandihwtest.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.brandihwtest.repository.pojo.ImageData
import com.example.brandihwtest.repository.pojo.MetaData
import com.example.brandihwtest.repository.response.KakaoImageResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dapi.kakao.com/"

class RetrofitRepository(val m_Context: Context) {
    private var m_retrofit: Retrofit
    private var m_retrofitServiceAPI: IRetrofitAPI

    val m_kakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }
    val m_moreKakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }

    init {
        //RequestHeader
        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val builder = chain.request().newBuilder()
                    .header("Authorization", "KakaoAK 1fbafbbc5d278c7c38edf13a79204989").build()
                return chain.proceed(builder)
            }
        }).build()

        //Retrofit 설정
        m_retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).
        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(httpClient).build()
        m_retrofitServiceAPI = m_retrofit.create(IRetrofitAPI::class.java)
    }

    fun getImageLists(query:String)
    {
        m_retrofitServiceAPI.getImageLists(query, "accuracy", 1, 30).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnError{
//                Toast.makeText(m_Context, "통신 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                m_kakaoImageResponse.postValue(KakaoImageResponse(ArrayList<ImageData>(), MetaData()))
            }
            .subscribe({
                m_kakaoImageResponse.postValue(it)
            }, {})

//        call.enqueue(object :Callback<KakaoImageResponse>
//        {
//            override fun onResponse(
//                call: Call<KakaoImageResponse>,
//                response: Response<KakaoImageResponse>
//            ) {
//                if(response.isSuccessful) {
//                    val imageListItems = response.body()
//                    m_kakaoImageResponse.postValue(imageListItems)
//                }
//            }
//
//            override fun onFailure(call: Call<KakaoImageResponse>, t: Throwable) {
//            }
//        })
    }

    fun addImageLists(query:String, page:Int) {
        val call = m_retrofitServiceAPI.getImageLists(query, "accuracy", page, 30)
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnError{
//                Toast.makeText(m_Context, "통신 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                m_moreKakaoImageResponse.postValue(KakaoImageResponse(ArrayList<ImageData>(), MetaData()))
            }
            .subscribe({
                m_moreKakaoImageResponse.postValue(it)
            }, {})

//        call.enqueue(object : Callback<KakaoImageResponse> {
//            override fun onResponse(
//                call: Call<KakaoImageResponse>,
//                response: Response<KakaoImageResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val imageListItems = response.body()
//                    m_moreKakaoImageResponse.postValue(imageListItems)
//                }
//            }
//
//            override fun onFailure(call: Call<KakaoImageResponse>, t: Throwable) {
//            }
//        })
    }
}