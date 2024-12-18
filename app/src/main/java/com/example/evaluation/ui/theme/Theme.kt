package com.example.evaluation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.evaluation.R

@Composable
private fun getDarkColorScheme() = darkColorScheme(
    primary = colorResource(id = R.color.bright_red),
    onPrimary = colorResource(id = R.color.white),
    background = colorResource(id = R.color.cod_gray),
    onBackground = colorResource(id = R.color.white),
    error = colorResource(id = R.color.bright_red),
)

@Composable
private fun getLightColorScheme() = lightColorScheme(
    primary = colorResource(id = R.color.bright_red),
    onPrimary = colorResource(id = R.color.white),
    background = colorResource(id = R.color.white),
    onBackground = colorResource(id = R.color.black),
    error = colorResource(id = R.color.bright_red),
)

@Composable
fun EvaluationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> getDarkColorScheme()
        else -> getLightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}