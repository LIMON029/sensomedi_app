package com.limon.test.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("code")
    private val code:Int,

    @SerializedName("message")
    private val message:String,

    @SerializedName("accessToken")
    private val accessToken:String,

    @SerializedName("refreshToken")
    private val refreshToken:String
) {
    override fun toString(): String {
        return "TokenResponse(code:$code, message:$message,\naccessToken:$accessToken,\nrefreshToken:$refreshToken)"
    }
}