package com.example.newsapplication.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.remote.NewRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImp(val requestApi: RequestApi):NewRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
      return Pager(
           config = PagingConfig(pageSize = 10),
           pagingSourceFactory = {
               NewsPagingSource( requestApi = requestApi,
                   sourses = sources.joinToString(separator = ",")
               )
           }
      ).flow

    }

    override fun searchNews(
        searchPhrase: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    requestApi = requestApi,
                    searchQuery=searchPhrase,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow

    }
    }