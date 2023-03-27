package com.example.newsapp.data.api

import com.example.newsapp.utils.AppConstant.API_KEY
import com.example.newsapp.TopHeadlinesResponse
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse
}