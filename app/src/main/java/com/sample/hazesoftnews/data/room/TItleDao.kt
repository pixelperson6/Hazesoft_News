package com.sample.hazesoftnews.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.sample.hazesoftnews.domain.model.Title
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {

    @Query("SELECT * FROM Title")
    fun getTitles(): Flow<List<Title>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTitle(title: Title)

    @Delete
    suspend fun deleteTitle(title:Title)
}