package com.sample.hazesoftnews.presentation.news_details


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.hazesoftnews.common.openUrl
import com.sample.hazesoftnews.presentation.MainActivity
import com.sample.hazesoftnews.presentation.news_details.components.ArticleDetailsItem

@Composable
fun NewsDetailScreen(
    navController: NavController,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val activity = ( LocalContext.current as MainActivity)
    val article = activity.articles?.articles?.get(viewModel.index.value)
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {

                ArticleDetailsItem(article = article!!, onReadAllClick = {
                    activity.openUrl(it)
                })

            }
        }

    }

}