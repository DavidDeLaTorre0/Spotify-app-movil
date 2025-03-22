package com.example.aristi_firebase.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val BASE_URL = "https://www.hooksounds.com/edd-api/"
    private const val AUTH_TOKEN = "a13bfad9c4535d7b15a803c8383e3a8c"





    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
