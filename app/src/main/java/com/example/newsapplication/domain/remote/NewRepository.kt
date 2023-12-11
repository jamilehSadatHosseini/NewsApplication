package com.example.newsapplication.domain.remote

import androidx.paging.PagingData
import com.example.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(sources:List<String>): Flow<PagingData<Article>>
}