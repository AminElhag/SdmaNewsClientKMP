package common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import sdmanewsclientkmp.composeapp.generated.resources.Res
import sdmanewsclientkmp.composeapp.generated.resources.man
import ui.theme.textColor

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                Res.drawable.man
            ),
            contentDescription = "Error icon",
            modifier = Modifier.size(200.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.width(28.dp))
        Text(
            errorMessage,
            modifier = Modifier
                .padding(6.dp),
            color = textColor,
        )
        Spacer(modifier = Modifier.width(28.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("اعد المحاولة", color = textColor)
        }
    }
}