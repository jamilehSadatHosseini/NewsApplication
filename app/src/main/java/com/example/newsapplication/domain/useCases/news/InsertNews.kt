package com.example.newsapplication.domain.useCases.news

import com.example.newsapplication.data.local.NewsDao
import com.example.newsapplication.domain.model.Article

class InsertNews(private val newsDao: NewsDao) {
    suspend operator fun invoke(article: Article) {
         newsDao.insertArticle(article)
    }
}