package com.sample.hazesoftnews.data.repository

import com.sample.hazesoftnews.data.room.TitleDao
import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow

class TitleRepositoryImp(
    private val dao: TitleDao
) : TitleRepository {
    override fun getTitles(): Flow<List<Title>> {
        return dao.getTitles()
    }

    override suspend fun insertTitle(title: Title) {
        dao.insertTitle(title)
    }

    override suspend fun deleteTitle(title: Title) {
        dao.deleteTitle(title)
    }
}