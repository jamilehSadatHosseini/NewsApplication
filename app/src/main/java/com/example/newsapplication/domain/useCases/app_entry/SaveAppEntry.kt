package com.example.newsapplication.domain.useCases.app_entry

import com.example.newsapplication.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun  invoke(){
        localUserManager.saveAppEntry()
    }
}