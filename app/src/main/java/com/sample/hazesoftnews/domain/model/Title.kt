package com.sample.hazesoftnews.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Title(
    val title:String,
    @PrimaryKey val id:Int?=null
)

class InvalidTitleException(message:String):Exception(message)