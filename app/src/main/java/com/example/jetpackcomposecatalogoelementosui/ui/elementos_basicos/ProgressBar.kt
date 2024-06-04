package com.example.jetpackcomposecatalogoelementosui.ui.elementos_basicos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyProgressBarAdvanceExample() {

    var progress: Float by rememberSaveable { mutableFloatStateOf(0.0f) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        LinearProgressIndicator(
            progress = {
                progress
            },
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                enabled = (progress > 0f),
                onClick = {
                    progress = "%.1f".format(progress-0.1f).toFloat()
                }
            ) {
                Text(text = "Disminuir")
            }
            Button(
                enabled = (progress < 1f ),
                onClick = {
                    progress += 0.1f
                }
            ) {
                Text(text = "Incrementar")
            }
        }
    }
}

@Composable
fun MyProgressBarExample() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        var showLoading: Boolean by rememberSaveable() {
            mutableStateOf(false)
        }

        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 7.dp,
                modifier = Modifier
            )

            LinearProgressIndicator(
                color = Color.Blue,
                trackColor = Color.Green,
                modifier = Modifier
            )
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(
                text = "Cargar perfil"
            )
        }

    }
}