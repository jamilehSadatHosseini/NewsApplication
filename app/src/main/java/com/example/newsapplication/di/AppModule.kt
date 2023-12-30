package com.example.newsapplication.di

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.local.NewsDataBase
import com.example.newsapplication.data.local.NewsTypeConvertor
import com.example.newsapplication.data.manager.LocalUserManagerImpl
import com.example.newsapplication.data.remote.NewsRepositoryImp
import com.example.newsapplication.data.remote.RequestApi
import com.example.newsapplication.domain.manager.LocalUserManager
import com.example.newsapplication.domain.remote.NewRepository
import com.example.newsapplication.domain.useCases.app_entry.AppEntryUseCases
import com.example.newsapplication.domain.useCases.app_entry.ReadAppEntry
import com.example.newsapplication.domain.useCases.app_entry.SaveAppEntry
import com.example.newsapplication.domain.useCases.news.GetNewsUsecase
import com.example.newsapplication.domain.useCases.news.NewsUseCases
import com.example.newsapplication.domain.useCases.news.SearchNewsUsecase
import com.example.newsapplication.util.Constants.BASE_URL
import com.example.newsapplication.util.Constants.News_DataBase_Name
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            SaveAppEntry(localUserManager = localUserManager),
            ReadAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideRequestApi(): RequestApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)
    }

    @Provides
    @Singleton
    fun ProvidNewsRepository(requestApi: RequestApi): NewRepository =
        NewsRepositoryImp(requestApi = requestApi)

    @Provides
    @Singleton
    fun ProvidNewsUseCases(newsRepository: NewRepository): NewsUseCases =
        NewsUseCases(GetNewsUsecase(newsRepository), SearchNewsUsecase(newsRepository))

    @Provides
    @Singleton
    fun ProvidNewsDataBases(application: Application): NewsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = News_DataBase_Name
        ).addTypeConverter(NewsTypeConvertor()).fallbackToDestructiveMigration().build()

    }

    @Singleton
    @Provides
    fun NewsDao(dataBase: NewsDataBase)= dataBase.newsDao

}
