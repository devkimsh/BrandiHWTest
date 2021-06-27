package com.example.brandihwtest.repository.pojo

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("is_end")
    val is_end:Boolean = false,
    @SerializedName("pageable_count")
    val pageable_count:Int = 0,
    @SerializedName("total_count")
    val total_count:Int = 0,
) {
}