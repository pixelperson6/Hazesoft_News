package com.sample.hazesoftnews.presentation.saved_title.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.sample.hazesoftnews.domain.model.Title

@Composable
fun TitleItem(
    title:Title,
    modifier: Modifier = Modifier,
    cornerRadius:Dp = 10.dp,
    cutCornerSize:Dp = 30.dp,
    onDeleteClick:() -> Unit
) {
    Box {
        Canvas(modifier = Modifier.matchParentSize()){
            val clipPath = Path().apply {
                lineTo(size.width-cutCornerSize.toPx(),0f)
                lineTo(size.width,cutCornerSize.toPx())
                lineTo(size.width,size.height)
                lineTo(0f,size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    color = Color.Gray,
                    size= size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(Color.Gray.toArgb(),0x000000,0.2f)),
                    topLeft= Offset(size.width-cutCornerSize.toPx(),-100f),
                    size= Size(cutCornerSize.toPx() + 100f ,cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {

            Text(
                text = title.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete title",
                tint = MaterialTheme.colors.onSurface
            )
        }

    }

}