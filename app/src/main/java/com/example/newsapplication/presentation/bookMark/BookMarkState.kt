package com.example.newsapplication.presentation.bookMark

import com.example.newsapplication.domain.model.Article

data class BookMarkState(val articles: List<Article> = emptyList())