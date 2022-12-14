package com.sample.hazesoftnews.presentation.news_list.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sample.hazesoftnews.R
import com.sample.hazesoftnews.data.remote.dto.Article


@Composable
fun NewsListItem(
    article: Article,
    modifier: Modifier = Modifier,
    onItemClick: (article: Article) -> Unit,
    contentScale: ContentScale = ContentScale.FillBounds,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(article) },
    ) {
        Column {
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
                contentScale = contentScale,
                contentDescription = "article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            Column(Modifier.padding(8.dp)) {
                Text(text = article.title?:"not available", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text(article.description ?: "", style = MaterialTheme.typography.body1, maxLines = 4, overflow = TextOverflow.Ellipsis)
            }
        }

    }
}