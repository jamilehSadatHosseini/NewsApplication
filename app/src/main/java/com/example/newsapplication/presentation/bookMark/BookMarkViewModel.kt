package com.example.newsapplication.presentation.bookMark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BookMarkViewModel @Inject constructor( val newsUseCases: NewsUseCases): ViewModel() {

    private val _state= mutableStateOf(BookMarkState())
    val state : State<BookMarkState> = _state
init {
    getArticles()
}
    private fun getArticles(){
        newsUseCases.selectNews().onEach {
            _state.value = _state.value.copy(it)
        }.launchIn(viewModelScope

        )
    }
}