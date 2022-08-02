package com.sample.hazesoftnews.domain.model

import com.sample.hazesoftnews.data.remote.dto.Article


data class Articles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

