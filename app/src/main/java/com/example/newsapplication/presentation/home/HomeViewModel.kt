package com.example.newsapplication.presentation.home

import androidx.lifecycle.ViewModel
import com.example.newsapplication.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsUseCases: NewsUseCases):ViewModel() {

    val news=newsUseCases.getNews(
        sources = listOf("bbc-news","abc-news","al_jazeera-english")
    )
}