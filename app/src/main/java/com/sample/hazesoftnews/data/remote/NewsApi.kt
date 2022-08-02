package com.sample.hazesoftnews.data.remote


import com.sample.hazesoftnews.common.Constants.API_KEY
import com.sample.hazesoftnews.data.remote.dto.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("/v2/top-headlines?country=us&apiKey=$API_KEY")
    suspend fun getNews():ArticlesDto

    @GET("/v2/everything?q={query}&apiKey=$API_KEY")
    suspend fun getNewsByQuery(@Path("query") query:String) : ArticlesDto

}