package com.example.newsapplication.presentation.articleDetails

import com.example.newsapplication.domain.model.Article

sealed class DetailEvent {
data class SaveArticleEvent(val article: Article):DetailEvent()
object RemoveSideEffect:DetailEvent()

}