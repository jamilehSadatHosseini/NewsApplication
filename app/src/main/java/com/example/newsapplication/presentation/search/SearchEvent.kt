package com.example.newsapplication.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val phrase:String):SearchEvent()

    object SearchNews:SearchEvent()
}