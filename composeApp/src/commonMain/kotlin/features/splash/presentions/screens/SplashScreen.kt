package features.splash.presentions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import sdmanewsclientkmp.composeapp.generated.resources.Mirza_SemiBold
import sdmanewsclientkmp.composeapp.generated.resources.Res
import ui.theme.primaryDark

@Composable
fun SplashScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryDark)
            .statusBarsPadding(),
    ) {
        Text(
            "سدما",
            fontSize = 60.sp,
            fontFamily = FontFamily(Font(Res.font.Mirza_SemiBold, weight = FontWeight.SemiBold))
        )
        Text(
            "مصداقة • سرعة • كفاء",
            fontSize = 30.sp,
        )
    }
}

