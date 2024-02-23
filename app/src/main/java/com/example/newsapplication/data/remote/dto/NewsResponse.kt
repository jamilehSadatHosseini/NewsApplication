package com.example.newsapplication.data.remote.dto

import com.example.newsapplication.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)