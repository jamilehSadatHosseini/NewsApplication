package com.example.newsapplication.di

import android.app.Application
import com.example.newsapplication.data.manager.LocalUserManagerImpl
import com.example.newsapplication.domain.manager.LocalUserManager
import com.example.newsapplication.domain.useCases.AppEntryUseCases
import com.example.newsapplication.domain.useCases.ReadAppEntry
import com.example.newsapplication.domain.useCases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
     fun provideLocalUserManager(application:Application):LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
     fun provideAppEntryUseCases(localUserManager: LocalUserManager):AppEntryUseCases= AppEntryUseCases(SaveAppEntry(localUserManager =localUserManager),
        ReadAppEntry(localUserManager))


}
