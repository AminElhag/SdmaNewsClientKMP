package features.newsDetails.presentaions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.size.Size
import common.components.shimmerEffect

@Composable
fun ShimmerNewsDetails(
    modifier: Modifier
) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).shimmerEffect(),
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start)
                    .padding(top = 6.dp).size(16.dp).shimmerEffect(),
            )
            Box(
                modifier = Modifier.fillMaxWidth(.75f).padding(top = 6.dp).size(16.dp)
                    .shimmerEffect(),
            )
            Box(
                modifier = Modifier.fillMaxWidth(.50f).padding(top = 6.dp).size(16.dp)
                    .shimmerEffect(),
            )
            LazyColumn {
                items(10) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp).size(16.dp).shimmerEffect(),
                    )
                }
            }
        }
    }
}