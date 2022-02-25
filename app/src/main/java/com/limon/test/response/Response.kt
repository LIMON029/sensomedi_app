package com.limon.test.response

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("code")
    private val code:Int,

    @SerializedName("message")
    private val message:String,

    @SerializedName("data")
    private val data:String
){
    override fun toString(): String {
        return "Response(code:$code, message:$message, data:$data)"
    }

    fun getCode():Int = code
    fun getMessage():String = message
    fun getData():String = data
}