package com.example.jokesapp.repository

import com.example.jokesapp.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper:ApiHelper
){

    suspend fun getJokes() = apiHelper.getJokes()

}