package com.example.newsapplication.presentation.onbording

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.useCases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases):ViewModel() {

    fun onEvent( event: OnBoardingEvent){
        when(event)
        {
           is OnBoardingEvent.SaveAppEntryEvent->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry.invoke()
        }
    }
}