package com.example.newsapplication.domain.useCases.app_entry

import com.example.newsapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

   operator fun  invoke(): Flow<Boolean> {
       return localUserManager.readAppEntry()
   }
}