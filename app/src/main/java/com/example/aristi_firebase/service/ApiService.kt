package com.example.aristi_firebase.service

import com.example.aristi_firebase.model.ProductResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://www.hooksounds.com/edd-api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface ApiService {

    @Headers(
        "Authorization: Bearer a13bfad9c4535d7b15a803c8383e3a8c",
        "Content-Type: application/json"
    )
    @GET("products/?number=100")
    suspend fun getProducts(): ProductResponse

}