package com.example.newsapplication.domain.useCases.news

data class NewsUseCases(
    val getNews: GetNewsUseCase,
    val searchNews: SearchNewsUsecase,
    val insertNews: InsertNews,
    val deleteNews: DeleteNews,
    val selectArticles: SelectArticles,
    val selectArticle:SelectArticle
)
