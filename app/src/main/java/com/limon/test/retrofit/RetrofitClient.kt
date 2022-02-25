package com.limon.test.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "http://192.168.0.31:8080"
    //192.168.0.31 -> 이 부분은 서버의 ipv4주소

    val instance: RetrofitService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitService::class.java)
    }
}