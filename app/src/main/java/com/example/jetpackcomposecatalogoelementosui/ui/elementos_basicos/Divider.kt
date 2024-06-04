package com.example.jetpackcomposecatalogoelementosui.ui.elementos_basicos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyDividerExample() {

    HorizontalDivider(
        modifier = Modifier
            .padding(top = 64.dp),
        thickness = 4.dp,
        color = Color.Red
    )

    Row {
        Text(
            text = "Ejemplo 1"
        )
        VerticalDivider( //Divisor vertical
            thickness = 4.dp,
            color = Color.Red,
            modifier = Modifier
                .padding(start = 32.dp, top = 64.dp)
        )
        Text(
            text = "Ejemplo 2"
        )
    }
}

@Preview()
@Composable
fun MyDividerPreviewExample(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyDividerExample()
        }
    }
}