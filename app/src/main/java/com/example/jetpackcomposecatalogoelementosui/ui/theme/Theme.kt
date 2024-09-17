package com.example.jetpackcomposecatalogoelementosui.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JetPackComposeCatalogoElementosUiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current//OBTENEMOS LA VISTA DEL COMPOSABLE ACTUAL, EN ESTE CASO EL COMPOSABLE DE TEMA

    val contexto = LocalContext.current

    if (!view.isInEditMode) {//VALIDAMOS SI NO ESTA EN MODO EDICIÓN Y SE ESTA USANDO EN UN DISPOSITIVO REAL
        SideEffect {

            val window = (view.context as Activity).window //OBTENEMOS LA VENTANA DONDE SE MUESTRA LA ACTIVIDAD

            //window.statusBarColor = Color.Transparent.toArgb()//SI ESTAMOS USANDO EDGE TO EDGE CAMBIAMOS EL COLOR DESDE ESA FUNCIÓN

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme //COLOR DE LOS ICONOS DE LA BARRA DE ESTADO DEPENDIENDO DEL MODO

        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}