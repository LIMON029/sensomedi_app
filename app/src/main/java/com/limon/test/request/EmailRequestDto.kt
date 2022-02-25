package com.limon.test.request

import com.google.gson.annotations.SerializedName

data class EmailRequestDto(
    @SerializedName("address")
    var address: String
)