package com.example.hooksounds_tfg.data.remote

object ServiceBuilder {

    private const val BASE_URL = "https://www.hooksounds.com/edd-api/"
    private const val AUTH_TOKEN = "a13bfad9c4535d7b15a803c8383e3a8c"





    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
