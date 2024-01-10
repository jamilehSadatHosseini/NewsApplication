package com.example.newsapplication.domain.useCases.news

import com.example.newsapplication.data.local.NewsDao
import com.example.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectNews(private val newsDao: NewsDao) {
    operator fun invoke():Flow<List<Article>> {
       return  newsDao.selectedArticle()
    }
}