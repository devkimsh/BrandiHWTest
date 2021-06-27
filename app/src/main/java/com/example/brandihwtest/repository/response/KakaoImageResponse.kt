package com.example.brandihwtest.repository.response

import com.example.brandihwtest.repository.pojo.ImageData
import com.example.brandihwtest.repository.pojo.MetaData
import com.google.gson.annotations.SerializedName

data class KakaoImageResponse(
    @SerializedName("documents")
    var documents:ArrayList<ImageData>,
    @SerializedName("meta")
    var meta: MetaData
)
{
    init {
        documents = arrayListOf<ImageData>()
    }
}