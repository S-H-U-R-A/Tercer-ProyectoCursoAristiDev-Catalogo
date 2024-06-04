package com.example.jetpackcomposecatalogoelementosui.efectos_secundarios

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MySideEffectExample() {

    var clickCount by remember { mutableIntStateOf(value = 0) }

    // Ejecuta un efecto secundario después de cada recomposición
    SideEffect {
        Log.d("SideEffectExample", "MyComposable recomposed! clickCount: $clickCount")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { clickCount++ }) {
                Text("Click me")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Button clicked $clickCount times")
        }
    }

/*    CompositionLocalProvider(
        values = arrayOf(
            LocalSpacing provides Spacing(), //USANDO LA NOTACIÓN INFIX
            LocalCustomElevation.provides(ElevationLocal())
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = LocalSpacing.current.large)
        ) {
            TextField(
                value = txtSaludar,
                onValueChange = {
                    txtSaludar = it
                }
            )
            Text(text = txtSaludar)
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = LocalCustomElevation.current.card)
            ) {
                Text(text = "Hola desde la tarjeta")
            }
            Button(onClick = { uriHandler.openUri("https://www.example.com") }) {
                Text(text = "Ir")
            }

            Text(
                text = "Esto es un texto de ejemplo",
            )

        }
    }*/

}


@Preview(
    name = "Example tipes of modifiers",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MySideEffectExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MySideEffectExample()
        }
    }
}