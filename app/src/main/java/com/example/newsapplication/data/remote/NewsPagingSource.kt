package com.example.newsapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapplication.domain.model.Article

class NewsPagingSource(
    val requestApi: RequestApi,
    val sourses: String
) : PagingSource<Int, Article>() {

    private var totalCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
      return  state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val news = requestApi.getNews(page = page, sources = sourses)
            totalCount += news.articles.size
            val article = news.articles.distinctBy { it.title }
            LoadResult.Page(
                data = article,
                nextKey = if(totalCount == news.totalResults) null else page+1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}