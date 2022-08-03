package com.sample.hazesoftnews.presentation.news_list

import com.sample.hazesoftnews.domain.model.Articles


data class NewsListState(
    val isLoading:Boolean = false,
    val news:Articles = Articles(emptyList(),"",0),
    val error:String = ""
)
