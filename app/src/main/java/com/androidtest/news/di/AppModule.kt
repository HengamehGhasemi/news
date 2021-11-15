package com.androidtest.news.di

import android.content.Context
import androidx.room.Room
import com.androidtest.news.cache.database.AppDatabase
import com.androidtest.news.cache.dao.NewsDao
import com.androidtest.news.network.NewsService
import com.androidtest.news.network.repository.GetNewsRepository
import com.androidtest.news.ui.viewModel.NewsViewModel
import com.androidtest.news.utils.ApiServicesUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Singleton
    @Provides
    fun provideNewsRepository(
        dao: NewsDao,
        api: NewsService,
    ) = GetNewsRepository(dao, api)

    @Singleton
    @Provides
    fun provideNewsViewModel(
        repository: GetNewsRepository,
        dao: NewsDao,
    ) = NewsViewModel(repository,dao)

    @Singleton
    @Provides
    fun provideNewsDao(
        database: AppDatabase
    ) = database.newsDao()

    @Singleton
    @Provides
    fun provideFavoriteNewsDao(
        database: AppDatabase
    ) = database.favoritesNewsDao()

    @Singleton
    @Provides
    fun providePlaceService(): NewsService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiServicesUrl)
            .build()
            .create(NewsService::class.java)
    }
}