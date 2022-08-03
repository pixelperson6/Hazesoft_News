package com.sample.hazesoftnews.data.remote


import com.sample.hazesoftnews.common.Constants.API_KEY
import com.sample.hazesoftnews.data.remote.dto.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(@Query("country") country:String = "us",@Query("apiKey") apiKey:String= API_KEY):ArticlesDto

    @GET("/v2/everything")
    suspend fun getNewsByQuery(@Query("q") query:String,@Query("apiKey") apiKey:String= API_KEY) : ArticlesDto

}