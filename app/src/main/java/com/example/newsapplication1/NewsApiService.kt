package com.example.newsapplication1

import com.example.newsapplication1.models.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService{

        @GET("top-headlines")
        suspend fun getEverything(
            @Query("apiKey") apiKey: String,
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("pageSize") pageSize: Int
        ): Response<NewsApiResponse>

        @GET("top-headlines")
        suspend fun getSearchResult(
            @Query("apiKey") apikey: String,
            @Query("q") query: String,
            @Query("pageSize") pageSize: Int
        ): Response<NewsApiResponse>

}
