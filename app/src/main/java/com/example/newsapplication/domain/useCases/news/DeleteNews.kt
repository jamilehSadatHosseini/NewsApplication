package com.example.newsapplication.domain.useCases.news

import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewRepository

class DeleteNews(private val repository: NewRepository) {
    suspend operator fun invoke(article: Article) {
         repository.deleteArticle(article)
    }
}