package com.sample.hazesoftnews.presentation.news_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sample.hazesoftnews.R
import com.sample.hazesoftnews.data.remote.dto.Article


@Composable
fun ArticleDetailsItem(article: Article, onReadAllClick: (articleUrl: String) -> Unit,onSavedClick:(title:String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterStart)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArticleTitle(title = article.title)
        Image(
            painter = rememberImagePainter(
                data = article.urlToImage,
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
        ArticleDescription(article.description)
        ArticleContent(article.content)
        ReadAllButton { onReadAllClick.invoke(article.url) }
        ReadAllButton { onSavedClick(article.title) }


    }
}

@Composable
private fun ArticleTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ArticleDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ArticleContent(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ReadAllButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text(text = "Read full article") },
        modifier = Modifier.padding(all = 16.dp)
    )
}

