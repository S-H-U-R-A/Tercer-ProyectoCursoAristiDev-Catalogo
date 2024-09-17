package com.example.jetpackcomposecatalogoelementosui.ui.contenedores

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyCardExample() {
    Card(
        elevation=CardDefaults.cardElevation(
            defaultElevation = 16.dp,
            pressedElevation = 8.dp
        ),
        colors=CardDefaults.cardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Red
        ),
        shape=CutCornerShape(size=25f),
        border=BorderStroke(
            width=2.dp,
            color=Color.Red
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }
    }
}

@Composable
fun MyCardOverloadExample(){
    val context = LocalContext.current

    Card(
        onClick = { Toast.makeText(context, "Click en la tarjeta", Toast.LENGTH_SHORT).show() },
        enabled = true,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = Color.Blue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }
    }
}



@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyCardExamplesPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyCardOverloadExample()
        }
    }
}