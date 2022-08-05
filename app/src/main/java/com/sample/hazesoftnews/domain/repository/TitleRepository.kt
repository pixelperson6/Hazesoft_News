package com.sample.hazesoftnews.domain.repository



import com.sample.hazesoftnews.domain.model.Title
import kotlinx.coroutines.flow.Flow

interface TitleRepository {

    fun getTitles(): Flow<List<Title>>

    suspend fun insertTitle(title: Title)

    suspend fun deleteTitle(title:Title)

}