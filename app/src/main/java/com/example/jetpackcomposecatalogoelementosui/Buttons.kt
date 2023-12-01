package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyButtonsExample() {

    var enabled: Boolean by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Button(
            onClick = {
                enabled = false
            },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(width = 5.dp, color = Color.Green),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Hola"
            )
        }

        OutlinedButton(
            onClick = {
                enabled = false
            },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta, //Color del fondo del botón habilitado
                contentColor = Color.Blue, //Color del texto de botón habilitado
                disabledContainerColor = Color.Blue,//Color del fondo del botón deshabilitado
                disabledContentColor = Color.Red //Color del texto de botón deshabilitado
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Hola"
            )
        }

        TextButton(
            onClick = {}
        ) {
            Text(
                text = "Hola"
            )
        }

    }
}