package com.example.jokesapp.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("api")
    suspend fun getJokes():Response<String>


}