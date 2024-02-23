package com.example.newsapplication.presentation.articleDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailEvent){
        when(event){
            is DetailEvent.SaveArticleEvent->{
                viewModelScope.launch(Dispatchers.IO) {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        newsUseCases.insertNews(event.article)
                        sideEffect="article was inserted"
                    } else {
                        newsUseCases.deleteNews(article)
                        sideEffect= "article was deleted"

                    }
                }
            }
            is DetailEvent.RemoveSideEffect->{
                sideEffect=null
            }
        }
    }
}