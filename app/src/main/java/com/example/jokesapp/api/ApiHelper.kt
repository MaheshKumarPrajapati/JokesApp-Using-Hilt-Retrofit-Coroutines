package com.example.jokesapp.api

import retrofit2.Response

interface ApiHelper {

    suspend fun getJokes():Response<String>

}