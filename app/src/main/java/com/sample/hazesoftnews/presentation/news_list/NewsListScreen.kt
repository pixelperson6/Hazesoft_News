package com.sample.hazesoftnews.presentation.news_list


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.presentation.AppDrawer
import com.sample.hazesoftnews.presentation.MainActivity
import com.sample.hazesoftnews.presentation.Screen
import com.sample.hazesoftnews.presentation.TopAppBar
import com.sample.hazesoftnews.presentation.news_list.components.NewsListItem
import com.sample.hazesoftnews.presentation.util.SearchBarState
import kotlinx.coroutines.launch

@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val newsState = viewModel.state.value
    (LocalContext.current as MainActivity).articles = newsState.news

    val state = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var currentScreen by remember {
        mutableStateOf("Home")
    }

    Scaffold(
        scaffoldState = state,
        topBar = {
            /* TopAppBar(
                 title = { Text(text = "SoftHaze News") },
                 navigationIcon = {
                     IconButton(onClick = {
                         coroutineScope.launch { state.drawerState.open() }
                     }) {
                         Icon(Icons.Default.Menu, contentDescription = null)
                     }
                 }
             )*/
            TopAppBar(
                searchBarState = viewModel.searchBarState.value,
                searchTextState = viewModel.searchTextState.value,
                state = state,
                scope = coroutineScope
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
        drawerContent = {
            AppDrawer(
                currentScreen = currentScreen,
                navigateToHome = {
                    currentScreen = "Home"
                    navController.navigate(Screen.NewsListScreen.route)
                },
                navigateToSaved = {
                    currentScreen = "Saved"
                    navController.navigate(Screen.SavedTitleScreen.route)
                },
                closeDrawer = { coroutineScope.launch { state.drawerState.close() } }
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {

                if (newsState.news.articles.isNotEmpty()) {

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            NewsStatus(
                                count = newsState.news.totalResults,
                                status = newsState.news.status
                            )
                        }
                        itemsIndexed(newsState.news.articles) { index, article ->
                            NewsListItem(
                                article = article,
                                onItemClick = {
                                    navController.navigate(
                                        Screen.NewsDetailScreen.route +
                                                "?$PARAM_INDEX=${index}"
                                    )


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
                if (newsState.error.isNotBlank()) {
                    Text(
                        text = newsState.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .align(Center)
                    )
                }
                if (newsState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Center)
                    )
                }

            }
        })

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