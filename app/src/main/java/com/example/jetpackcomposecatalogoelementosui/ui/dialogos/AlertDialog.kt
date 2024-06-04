package com.example.jetpackcomposecatalogoelementosui.ui.dialogos

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyAlertDialogBasicExample(
    stateDialog: Boolean,
    onConfirmButtonClicked: (Boolean) -> Unit,
    onCancelButtonClicked: (Boolean) -> Unit
) { //Este elemento no se oculta o no, se crea o no se crea asi de simple
    if (stateDialog) {
        AlertDialog(
            onDismissRequest = { onCancelButtonClicked(false) },
            confirmButton = {
                TextButton( onClick = {onConfirmButtonClicked(false)} ) {
                    Text(text = "Confirmar" )
                }
            },
            dismissButton = {
                TextButton(onClick = {onCancelButtonClicked(false)} ) {
                    Text(text = "Cerrar")
                }
            },
            title = {
                Text(text = "Titulo")
            },
            text = {
                Text(text = "Contenido del dialogo")//Acá irá el contenido principal de la alerta
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null
                )
            },
            shape = CutCornerShape(size = 25.dp),
            containerColor = Color.LightGray,
            titleContentColor = Color.Magenta,
            textContentColor = Color.Red,
            tonalElevation = 5.dp,
            properties = DialogProperties()
        )
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true, showBackground = true
)
@Composable
fun MyAlertDialogExamplePreview() {

    var stateDialog: Boolean by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { stateDialog = !stateDialog }
        ) {
            Text(text = "Abrir Alerta")
        }
    }

    MyAlertDialogBasicExample(
        stateDialog = stateDialog,
        onConfirmButtonClicked = {
            stateDialog = it
        },
        onCancelButtonClicked = {
            stateDialog = it
        }
    )
}