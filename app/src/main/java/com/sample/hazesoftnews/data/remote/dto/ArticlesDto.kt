package com.sample.hazesoftnews.data.remote.dto

import com.sample.hazesoftnews.domain.model.Articles

data class ArticlesDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

fun ArticlesDto.toArticles(): Articles {
    return Articles(
        articles = articles,
        status = status,
        totalResults=totalResults
    )
}

