package com.example.newsapplication.domain.repository

import androidx.paging.PagingData
import com.example.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(sources:List<String>): Flow<PagingData<Article>>
    fun searchNews(searchPhrase:String,sources:List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

      fun selectArticles():Flow<List<Article>>

     suspend fun selectArticle(url:String):Article?

    suspend fun  deleteArticle(article:Article)
}