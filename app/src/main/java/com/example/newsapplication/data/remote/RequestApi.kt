package com.example.newsapplication.data.remote

import com.example.newsapplication.data.remote.dto.NewsResponse
import com.example.newsapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
        ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q")searchPhrase:String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponse
}