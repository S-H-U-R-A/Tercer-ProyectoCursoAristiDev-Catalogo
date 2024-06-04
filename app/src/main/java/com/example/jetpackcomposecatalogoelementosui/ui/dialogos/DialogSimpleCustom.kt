package com.example.jetpackcomposecatalogoelementosui.ui.dialogos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MySimpleCustomDialogExample(
    stateDialog: Boolean,
    onCancelButtonClicked: (Boolean) -> Unit
) {
    if (stateDialog) {
        Dialog(
            onDismissRequest = {
                onCancelButtonClicked(false)
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red.copy(alpha = 0.7f))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Esto es un ejemplo 1",
                        color = Color.Red
                    )
                    Text(
                        text = "Esto es un ejemplo 2",
                        color = Color.Green
                    )
                    Text(
                        text = "Esto es un ejemplo 3",
                        color = Color.Blue
                    )
                    Button(onClick = { onCancelButtonClicked(false) }) {
                        Text(text = "Cerrar")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MySimpleCustomDialogPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            var stateDialog: Boolean by remember { mutableStateOf(false) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { stateDialog = true }
                ) {
                    Text(text = "Abrir Alerta")
                }
            }

            MySimpleCustomDialogExample(
                stateDialog = stateDialog,
                onCancelButtonClicked = { stateDialog = false }
            )
        }
    }
}