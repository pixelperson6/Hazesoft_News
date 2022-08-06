package com.sample.hazesoftnews.presentation.news_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.sample.hazesoftnews.R
import com.sample.hazesoftnews.common.openUrl
import com.sample.hazesoftnews.data.remote.dto.Article
import com.sample.hazesoftnews.data.remote.dto.Source
import com.sample.hazesoftnews.presentation.MainActivity
import com.sample.hazesoftnews.presentation.news_details.NewsDetailViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as MainActivity)
    val article = activity.articles?.articles?.get(viewModel.index.value) ?: Article(
        "", "", "", "",
        Source("", ""), "", "", ""
    )
    var isSaved by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()
    Scaffold(topBar = {
        DetailTopAppBar() {
            navController.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = article.urlToImage ?: "not available",
                    onExecute = { _, _ -> true },
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_foreground)
                        error(R.drawable.ic_launcher_foreground)
                    }
                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = "article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author ?: "not available")
                InfoWithIcon(
                    icon = Icons.Default.DateRange,
                    info = /*stringToDate(*/article.publishedAt
                        ?: "2021-11-04T04:42:40Z"/*).getTimeAgo()*/
                )
                InfoWithIcon(Icons.Default.Place, info = article.source.name ?: "not available")

            }
            Text(text = article.title ?: "not available", fontWeight = FontWeight.Bold)
            Text(
                text = article.description ?: "not available",
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = article.content ?: "not available",
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { activity.openUrl(article.url ?: "https://newsapi.org/") },
                    content = { Text(text = "read full article") },
                    modifier = Modifier.padding(all = 16.dp)
                )
                Button(
                    onClick = {
                        isSaved = true
                        viewModel.saveTitle(article.title ?: "")
                    },
                    content = { Text(text = if(isSaved) "Saved" else "Save") },
                    enabled = !isSaved,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }


        }
    }
}

@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
            }
        })
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(
                id = R.color.purple_500
            )
        )
        Text(text = info)
    }
}

