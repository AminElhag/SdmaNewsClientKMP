package features.main.presentions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import common.DateUtil
import features.main.domain.NewsModel
import kotlinx.datetime.Clock
import ui.theme.textColor

@Composable
fun NewsItem(item: NewsModel) {
    Row {
        println(Clock.System.now().toString())
        SubcomposeAsyncImage(
            modifier = Modifier.size(100.dp).padding(start = 16.dp, top = 16.dp),
            model = item.imageUrl,
            contentDescription = null,
            loading = {
                CircularProgressIndicator()
            },
            error = {
                AsyncImage(
                    model = Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Warning"
                    ), contentDescription = "Warning"
                )
            }
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End,
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Text(
                    item.title,
                    modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start)
                        .padding(top = 6.dp),
                    textAlign = TextAlign.Justify,
                    color = textColor
                )
                Text(
                    DateUtil.getDateInBeautyWay(item.updateTime),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 6.dp),
                    color = textColor
                )
            }

        }
    }
}