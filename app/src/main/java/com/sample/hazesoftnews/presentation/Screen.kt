package com.sample.hazesoftnews.presentation

sealed class Screen(val route:String){
    object NewsListScreen: Screen("news_list_screen")
    object NewsDetailScreen: Screen("news_detail_screen")
    object SavedTitleScreen:Screen("saved_title_screen")
}