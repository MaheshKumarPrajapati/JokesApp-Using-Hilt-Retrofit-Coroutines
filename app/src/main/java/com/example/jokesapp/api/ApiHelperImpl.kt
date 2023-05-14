package com.example.jokesapp.api

import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{

    override suspend fun getJokes(): Response<String>  = apiService.getJokes()

}