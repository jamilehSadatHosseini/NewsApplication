package com.example.newsapplication.domain.useCases.news

import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(private val repository: NewRepository) {
    operator fun invoke():Flow<List<Article>> {
       return  repository.selectArticles()
    }
}