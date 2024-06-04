package com.example.jetpackcomposecatalogoelementosui.ui.dialogos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.jetpackcomposecatalogoelementosui.ui.formularios.opciones_de_seleccion.MyRadioButtonMultipleExample
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyConfirmationDialogExample(
    stateDialog: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (stateDialog) {
        var optionSelected: String by rememberSaveable { mutableStateOf("") }

        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(percent = 2)
                    )
            ) {

                MyTitleCustomDialog(
                    text = "Phone RingTone",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 24.dp)
                )

                HorizontalDivider(
                    modifier = Modifier
                        .alpha(0.8f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                MyRadioButtonMultipleExample(
                    optionSelected = optionSelected,
                    onOptionSelected = { option: String -> optionSelected = option },
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .height(height = 200.dp)
                        .verticalScroll(rememberScrollState())
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.5f),
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(height = 16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.End),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "CANCEL",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    TextButton(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "OK",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Preview()
@Composable
fun MyDialogConfirmationExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            var stateDialog: Boolean by  remember { mutableStateOf(false) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {stateDialog = !stateDialog}
                ) {
                    Text(text = "Abrir Alerta")
                }
            }

            MyConfirmationDialogExample(
                stateDialog = stateDialog,
                onDismiss = { stateDialog = false }
            )
        }
    }
}

