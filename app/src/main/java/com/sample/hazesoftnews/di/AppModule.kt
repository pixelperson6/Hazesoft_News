package com.sample.hazesoftnews.di

import com.sample.hazesoftnews.common.Constants
import com.sample.hazesoftnews.data.remote.NewsApi
import com.sample.hazesoftnews.data.repository.NewsRepositoryImpl
import com.sample.hazesoftnews.domain.repository.NewsRepository
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
    fun providesPaprikaApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:NewsApi):NewsRepository{
        return NewsRepositoryImpl(api)
    }

}