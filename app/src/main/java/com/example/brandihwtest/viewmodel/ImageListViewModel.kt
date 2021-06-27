package com.example.brandihwtest.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.brandihwtest.repository.RetrofitRepository
import com.example.brandihwtest.repository.pojo.ImageData
import com.example.brandihwtest.repository.pojo.MetaData
import com.example.brandihwtest.repository.response.KakaoImageResponse

class ImageListViewModel(m_Context: Context, lifeCycleOwner: LifecycleOwner):ViewModel() {
    private var m_retrofitRepository: RetrofitRepository

    //이미지 리스트 데이터 (ImageData)
    val m_kakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }

    //추가 이미지 리스트 데이터 (ImageData)
    val m_moreKakaoImageResponse: MutableLiveData<KakaoImageResponse> by lazy {
        MutableLiveData<KakaoImageResponse>()
    }

    init {
        m_retrofitRepository = RetrofitRepository(m_Context)
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