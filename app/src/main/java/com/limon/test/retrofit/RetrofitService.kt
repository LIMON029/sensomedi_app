package com.limon.test.retrofit

import com.limon.test.request.EmailRequestDto
import com.limon.test.request.JoinRequestDto
import com.limon.test.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("/join/sendCheckEmail")
    fun sendCheckMail(@Body param: EmailRequestDto):Call<Response>

    @POST("/join")
    fun join(@Body param: JoinRequestDto):Call<Response>
}