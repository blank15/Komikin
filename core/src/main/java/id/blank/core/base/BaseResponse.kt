package id.blank.core.base

import com.google.gson.annotations.SerializedName

data class BaseResponse <T> (

    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:T?
)