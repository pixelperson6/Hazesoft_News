package com.sample.hazesoftnews.data.repository


import com.sample.hazesoftnews.data.remote.NewsApi
import com.sample.hazesoftnews.data.remote.dto.ArticlesDto
import com.sample.hazesoftnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) :NewsRepository{

    override suspend fun getNews(): ArticlesDto {
        return api.getNews()
    }

    override suspend fun getNewsByQuery(query: String): ArticlesDto {
        return api.getNewsByQuery(query)
    }
}