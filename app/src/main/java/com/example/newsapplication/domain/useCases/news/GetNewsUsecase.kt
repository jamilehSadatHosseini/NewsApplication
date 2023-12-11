package com.example.newsapplication.domain.useCases.news

import androidx.paging.PagingData
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.remote.NewRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUsecase(private val newsRepository: NewRepository) {
   operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}