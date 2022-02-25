package com.limon.test.request

import com.google.gson.annotations.SerializedName

data class JoinRequestDto(
    @SerializedName("email")
    var email:String,
    @SerializedName("username")
    var username:String,
    @SerializedName("password")
    var password:String,
    @SerializedName("passwordCheck")
    var passwordCheck:String,
    @SerializedName("code")
    var code:Int
)