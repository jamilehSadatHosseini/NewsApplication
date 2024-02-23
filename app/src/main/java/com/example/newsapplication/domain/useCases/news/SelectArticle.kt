package com.example.newsapplication.domain.useCases.news

import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewRepository

class SelectArticle(private val repository: NewRepository) {
    suspend operator fun invoke(url:String):Article? {
       return  repository.selectArticle(url)
    }
}