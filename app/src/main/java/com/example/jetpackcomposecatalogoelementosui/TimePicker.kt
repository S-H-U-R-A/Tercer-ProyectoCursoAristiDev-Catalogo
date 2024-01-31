package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerBasicExample(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var showTimePicker by remember { mutableStateOf(false) }

    val stateTimePicker = rememberTimePickerState(is24Hour = false)

    var timePickerMode by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = {
                showTimePicker = true
            },
            modifier = Modifier
                .align(alignment = Alignment.Center)
        ) {
            Text(text = "Configurar la Hora")
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { showTimePicker = false },
            onConfirm = {
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val time = LocalTime.of(stateTimePicker.hour, stateTimePicker.minute)
                Toast.makeText(context, formatter.format(time), Toast.LENGTH_LONG).show()
            },
            toggle = {
                IconButton(onClick = { timePickerMode = !timePickerMode }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardAlt,
                        contentDescription = null
                    )
                }
            }
        ) {
            if(timePickerMode){
                TimeInput(state = stateTimePicker)
            }else{
                TimePicker(
                    state = stateTimePicker,
                    layoutType = TimePickerLayoutType.Vertical,
                    colors = TimePickerDefaults.colors(),
                )
            }
        }
    }
}


/**
 * Time picker dialog
 *
 * @param title titulo para el dialogo.
 * @param onCancel lambda que se ejecuta en el botón cancelar.
 * @param onConfirm lambda que se ejecuta en el botón confirmar
 * @param toggle
 * @param content contenido del dialogo
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) { Text("Cancel") }
                    TextButton(
                        onClick = onConfirm
                    ) { Text("OK") }
                }
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyTimePickerBasicExamplePreviewLight() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyTimePickerBasicExample()
        }

    }
}