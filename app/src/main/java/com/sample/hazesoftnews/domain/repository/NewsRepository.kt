package com.sample.hazesoftnews.domain.repository

import com.sample.hazesoftnews.data.remote.dto.ArticlesDto


interface NewsRepository {

    suspend fun getNews():ArticlesDto

    suspend fun getNewsByQuery(query:String):ArticlesDto
}