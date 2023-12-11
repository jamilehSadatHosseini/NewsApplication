package com.example.newsapplication.presentation.home

import androidx.lifecycle.ViewModel
import com.example.newsapplication.domain.useCases.news.NewsUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsUsecases: NewsUsecases):ViewModel() {

    val news=newsUsecases.getNewsUsecase(
        sources = listOf("bbc-news","abc-news","al_jazeera-english")
    )
}