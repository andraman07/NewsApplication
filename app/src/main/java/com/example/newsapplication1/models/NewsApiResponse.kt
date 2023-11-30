package com.example.newsapplication1.models

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)
