package com.example.preappkmm.android.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.preappkmm.android.presentation.components.CircularLoading

private val LightThemeColors = lightColors(
    primary = Main600,
    primaryVariant = Main400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Secondary,
    onSecondary = Black2,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = JetBrainsMonoTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Grey1)
        ){
            content()
            CircularLoading(isDisplayed = displayProgressBar, verticalBias = 0.3f)
        }
    }
}