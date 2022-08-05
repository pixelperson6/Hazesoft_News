package com.sample.hazesoftnews.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.hazesoftnews.domain.model.Title

@Database(
    entities = [Title::class],
    version = 1,
    exportSchema = false
)
abstract class TitleDatabase:RoomDatabase() {
    abstract val titleDao:TitleDao
    companion object{
        const val DATABASE_NAME = "title_db"
    }
}