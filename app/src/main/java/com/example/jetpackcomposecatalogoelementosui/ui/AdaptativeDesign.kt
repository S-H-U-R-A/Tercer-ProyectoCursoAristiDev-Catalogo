package com.example.jetpackcomposecatalogoelementosui.ui

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.MainActivity
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MyFirstExampleAdaptive(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    //ESTO SE RECOMIENDA HACER EN EL COMPOSABLE RAIZ ES DECIR EN EL MAIN ACTIVITY PARA USAR THIS SONDE SE SOLICITA LA ACTIVIDAD
    val windowSizeClass: WindowSizeClass = calculateWindowSizeClass(activity = (LocalContext.current as MainActivity)  )

    val isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    Text(text = "Estamos en una pantalla compacta: $isCompact")

}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyExamplesAdaptivePreviews() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyFirstExampleAdaptive()
        }
    }
}