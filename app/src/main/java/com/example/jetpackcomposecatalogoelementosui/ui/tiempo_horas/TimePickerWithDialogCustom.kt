package com.example.jetpackcomposecatalogoelementosui.ui.tiempo_horas

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


/**
 * Dialogo de ejemplo para un Time picker ya que por defecto no tiene un composable para esto
 *
 * @param title titulo para el dialogo.
 * @param onCancel lambda que se ejecuta en el botón cancelar.
 * @param onConfirm lambda que se ejecuta en el botón confirmar
 * @param toggle
 * @param content contenido del dialogo
 */
@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(//USAMOS UN DIALOGO NORMAL PARA CREAR EL DIALOGO PARA EL TIMEPICKER
        onDismissRequest = { onCancel() },//CUANDO SE OCULTE EL DIALOGO SE EJECUTA LA LAMBDA PASADA
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(//SE USO SURFACE Y NO CARD PORQUE SURFACE NO TIENE ARGUMENTOS POR DEFECTO, ES DECIR ES UN POCO MÁS PERSONALIZABLE
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(intrinsicSize =  IntrinsicSize.Min)
                .height(intrinsicSize = IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(//TITULO
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,//USAMOS EL TEXTO DEL TITULO
                    style = MaterialTheme.typography.labelMedium
                )
                content()//LAMBDA QUE REPRESENTA EL CONTENIDO DEL DIALOGO, ES DECIR EL TIMEPICKER O TIMEINPUT
                Row(//CONTROLES DE CONFIRMACIÓN< CAMBIAR DE MODO DE SELECTOR DE LA FECHA Y CANCELACIÓN
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()//CONTROL DE CAMBIO DE MODO
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onCancel) { Text("Cancel") }
                    TextButton(onClick = onConfirm) { Text("OK") }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerBasicExample(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current//CONTEXTO

    var showTimePicker by remember { mutableStateOf(value = false) }//ESTADO PARA DIBUJAR O NO EL DIALOGO DEL TIMEPICKER

    val stateTimePicker = rememberTimePickerState(is24Hour = false)//ESTADO PARA EL TIME PICKER QUE PERMITE CONFIGURAR EL MISMO Y OBTENER INFORMACIÓN DE ESTE

    var timePickerMode by remember { mutableStateOf(value = false) }//ESTADO PARA VALIDAR SI SE MUESTRA EL PICKER O EL INPUT, EN EL DATEPICKER ESTO SE CONFIGURA EN EL ESTADO DEL MISMO

    Box {
        Button(
            onClick = { showTimePicker = true },
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Text(text = "Configurar la Hora")
        }
    }

    if (showTimePicker) {//MOSTRAR O NO EL DIALOGO DE SELECCION DE LA HORA
        TimePickerDialog(//DIALOGO PERSONALIZADO CREADO POR MI PARA
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
            if(timePickerMode){//AMBOS COMPOSABLES DE SELECCION DE HORA TRABAJARÁN CON EL MISMO ESTADO
                TimeInput(state = stateTimePicker)
            }else{
                TimePicker(
                    state = stateTimePicker,
                    layoutType = TimePickerLayoutType.Vertical,//EL TIME PICKER QUE ES MÁS VISUAL SE PUEDE CONFIGURAR EN VERTICAL U HORIZONTAL(USADO MÁS EN TABLET)
                    colors = TimePickerDefaults.colors(),
                )
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