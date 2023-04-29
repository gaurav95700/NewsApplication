package com.example.newsapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.newsapp.ApplicationContext
import com.example.newsapp.BaseUrl
import com.example.newsapp.NewsApplication
import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }


    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext appContext: Context): NewsDatabase =
        Room.databaseBuilder(appContext, NewsDatabase::class.java, "newsapp.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

}