package com.example.newsapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapplication.data.local.NewsDao
import com.example.newsapplication.data.remote.NewsPagingSource
import com.example.newsapplication.data.remote.RequestApi
import com.example.newsapplication.data.remote.SearchNewsPagingSource
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImp(val requestApi: RequestApi, private val newsDao: NewsDao): NewRepository {
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

    override suspend fun upsertArticle(article: Article) {
        newsDao.insertArticle(article)
    }

    override  fun selectArticles(): Flow<List<Article>> {
       return newsDao.selectArticles().onEach { it.reversed() }
    }

    override suspend fun selectArticle(url: String): Article? {
       return newsDao.selectArticle(url)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }
}