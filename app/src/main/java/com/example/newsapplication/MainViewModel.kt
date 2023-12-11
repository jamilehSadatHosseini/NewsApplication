package com.example.newsapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.useCases.app_entry.AppEntryUseCases
import com.example.newsapplication.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appEntryUseCases: AppEntryUseCases):ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
    init {
        appEntryUseCases.readAppEntry.invoke().onEach { shouldStateFromHomeScreen ->
            startDestination = if (shouldStateFromHomeScreen) {
                Route.NewsNavigation.route
            } else{
                Route.AppStartNavigation.route
            }


            delay(3000)
            splashCondition = false
        }.launchIn(viewModelScope)

    }
}