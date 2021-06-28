package com.example.brandihwtest.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.brandihwtest.repository.RetrofitRepository
import com.example.brandihwtest.repository.response.KakaoImageResponse
import org.koin.android.ext.android.inject

class ImageListViewModel ():ViewModel() {
    private lateinit var m_retrofitRepository: RetrofitRepository

    private lateinit var m_Context:Context
    private lateinit var m_LifeCycleOwner:LifecycleOwner

    //이미지 리스트 데이터 (ImageData)
    val m_kakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }

    //추가 이미지 리스트 데이터 (ImageData)
    val m_moreKakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }

    init {

    }

    fun setParams(context: Context, lifeCycleOwner: LifecycleOwner)
    {
        m_Context = context
        m_LifeCycleOwner = lifeCycleOwner

        m_retrofitRepository = RetrofitRepository()

        m_retrofitRepository.m_kakaoImageResponse.observe(lifeCycleOwner, Observer {
            m_kakaoImageResponse.postValue(it)
        })
        m_retrofitRepository.m_moreKakaoImageResponse.observe(lifeCycleOwner, Observer {
            m_moreKakaoImageResponse.postValue(it)
        })
    }

    fun getImageLists(query:String)
    {
        m_retrofitRepository.getImageLists(query)
    }

    fun addImageLists(query:String, page:Int)
    {
        m_retrofitRepository.addImageLists(query, page)
    }
}