package com.sample.hazesoftnews.di

import android.app.Application
import androidx.room.Room
import com.sample.hazesoftnews.common.Constants
import com.sample.hazesoftnews.data.remote.NewsApi
import com.sample.hazesoftnews.data.repository.NewsRepositoryImpl
import com.sample.hazesoftnews.data.repository.TitleRepositoryImp
import com.sample.hazesoftnews.data.room.TitleDatabase
import com.sample.hazesoftnews.domain.repository.NewsRepository
import com.sample.hazesoftnews.domain.repository.TitleRepository
import com.sample.hazesoftnews.domain.use_case.AddTitleUseCase
import com.sample.hazesoftnews.domain.use_case.DeleteTitleUseCase
import com.sample.hazesoftnews.domain.use_case.GetTitlesUseCase
import com.sample.hazesoftnews.domain.use_case.TitleUseCases
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
    fun provideNewsRepository(api:NewsApi):NewsRepository{
        return NewsRepositoryImpl(api)
    }

   @Provides
    @Singleton
    fun provideTitleDatabase(app: Application): TitleDatabase {
        return Room.databaseBuilder(
            app,
            TitleDatabase::class.java,
            TitleDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTitleRepository(db:TitleDatabase): TitleRepository {
        return TitleRepositoryImp(db.titleDao)
    }

    @Provides
    @Singleton
    fun provideTitleUseCases(repository: TitleRepository): TitleUseCases {
        return TitleUseCases(
            getTitles = GetTitlesUseCase(repository),
            deleteTitle = DeleteTitleUseCase(repository),
            addTitle = AddTitleUseCase(repository)
        )
    }

}