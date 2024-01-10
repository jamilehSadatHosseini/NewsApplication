package com.example.newsapplication.domain.useCases.news

data class NewsUseCases(
    val GetNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUsecase,
    val insertNews: InsertNews,
    val deleteNews: DeleteNews,
    val selectNews: SelectNews
)
