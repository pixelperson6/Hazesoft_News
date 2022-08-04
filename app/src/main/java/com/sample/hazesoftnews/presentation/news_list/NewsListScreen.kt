package com.sample.hazesoftnews.presentation.news_list


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.hazesoftnews.common.Constants.PARAM_AUTHOR
import com.sample.hazesoftnews.common.Constants.PARAM_CONTENT
import com.sample.hazesoftnews.common.Constants.PARAM_DECS
import com.sample.hazesoftnews.common.Constants.PARAM_IMAGE_URL
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.common.Constants.PARAM_PUBLISHED
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_ID
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_NAME
import com.sample.hazesoftnews.common.Constants.PARAM_TITLE
import com.sample.hazesoftnews.common.Constants.PARAM_URL
import com.sample.hazesoftnews.presentation.MainActivity
import com.sample.hazesoftnews.presentation.Screen
import com.sample.hazesoftnews.presentation.news_list.components.NewsListItem
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    (LocalContext.current as MainActivity).articles = state.news
    Box(modifier = Modifier.fillMaxSize()) {

        if (state.news.articles.isNotEmpty()) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    NewsStatus(count = state.news.totalResults, status = state.news.status)
                }
                itemsIndexed(state.news.articles) { index, article ->
                    NewsListItem(
                        article = article,
                        onItemClick = {
                            navController.navigate(Screen.NewsDetailScreen.route +
                                    "?$PARAM_INDEX=${index}")


                            /*  navController.navigate(Screen.NewsDetailScreen.route +
                                      "?$PARAM_SOURCE_ID=${it.source.id}" +
                                      "&$PARAM_TITLE=${it.title}" +
                                      "&$PARAM_AUTHOR=${it.author}" +
                                      "&$PARAM_CONTENT=${it.content}" +
                                      "&$PARAM_DECS=${it.description}" +
                                      "&$PARAM_SOURCE_NAME=${it.source.name}" +
                                      "&$PARAM_PUBLISHED=${it.publishedAt}" +
                                      "&$PARAM_URL=${URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())}" +
                                      "&$PARAM_IMAGE_URL=${URLEncoder.encode(it.urlToImage, StandardCharsets.UTF_8.toString())}"
                              )*/
                        }
                    )

                }
            }

        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Center)
            )
        }

    }

}


@Composable
fun NewsStatus(
    count: Int,
    status: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Total Results : $count",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = status,
            color = if (status == "ok") Color.Green else Color.Red,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
        )
    }
}