package com.example.brandihwtest.repository.pojo

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("collection")
    var collection:String = "",
    @SerializedName("datetime")
    var datetime:String = "",
    @SerializedName("display_sitename")
    var display_sitename:String = "",
    @SerializedName("doc_url")
    var doc_url:String = "",
    @SerializedName("height")
    var height:Int = 0,
    @SerializedName("image_url")
    var image_url:String = "",
    @SerializedName("thumbnail_url")
    var thumbnail_url:String = "",
    @SerializedName("width")
    var width:Int = 0
)
{
}