import androidx.compose.runtime.*
import features.splash.presentions.screens.SplashScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.AppTheme

@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {

    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

    }
}