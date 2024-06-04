package com.example.jetpackcomposecatalogoelementosui.ui.fechas

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialogDatePickerExampleBasic(
    modifier: Modifier = Modifier,
) {
    var showDateDialog: Boolean by remember { mutableStateOf(value = false) }//ESTADO PARA DIBUJAR O NO DIBUJAR EL DIALOGO

    val datePickerState = rememberDatePickerState(//ESTADO PARA EL DATEPICKER
        initialDisplayMode = DisplayMode.Input,
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (!showDateDialog) {//VALIDAMOS EL ESTADO DE DIBUJO
            Button(
                onClick = { showDateDialog = true }
            ) {
                Text(text = "Show DatePicker Dialog")
            }
            datePickerState.selectedDateMillis?.let {
                Text(
                    text = dateLongToString(datePickerState.selectedDateMillis!!, "dd/MM/yyyy"),
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                )
            }
        } else {
            DatePickerDialog(//DIALOGO QUE ACTUA DE ENVOLTORIO PARA EL DATEPICKER
                onDismissRequest = { showDateDialog = false },
                confirmButton = {
                    Button(
                        onClick = { showDateDialog = false }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDateDialog = false }
                    ) {
                        Text(text = "Cancelar")
                    }
                },
                shape = CutCornerShape(size = 25.dp)
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    colors = DatePickerDefaults.colors(
                        containerColor = Color.Cyan,
                        titleContentColor = Color.Red
                    )
                )
            }
        }
    }
}


//DESHABILITAR DIAS ESTA EN BETA
@ExperimentalMaterial3Api
@Composable
fun MyDialogDatePickerWithUnSelectableDaysExample(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
){
    //LISTA DE FECHAS A DESHABILITAR
    val listOfDateDisable: List<String> = listOf("17/05/2024", "18/05/2024")

    var dialogState: Boolean by rememberSaveable { mutableStateOf(value = false) }

    val datePickerState = rememberDatePickerState(//ESTADO PARA EL DATE PICKER
        initialSelectedDateMillis = Instant.now().toEpochMilli(),//OBTENEMOS LA FECHA ACTUAL EN MILISEGUNDOS
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {//POR CADA DIA DEL CALENDARIO DEVUELVE SU FECHA PARA VALIDARLA ALGO ASI COMO UN FOREACH EN EL CALENDARIO
                //SI NUESTRA LISTA DE FECHAS DESHABILITAS CONTIENE LA FECHA QUE NOS DEVUELVE EL MÉTODO RETORNAMOS FALSE PARA INHABILITARLA
                return !listOfDateDisable.contains(element = dateLongToString(utcTimeMillis,"dd/MM/yyyy" ))
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year>2022//DEVOLVEMOS TRUE SI HABILITAMOS UN AÑO Y FALSE PARA LO CONTRARIO, EN ESTE CASO SE DESHABILITAN LOS AÑOS QUE NO CUMPLAN LA CONDICIÓN
            }
        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        if(!dialogState){
            Button(
                onClick = { dialogState = true },
            ) {
                Text(text = "Mostrar Calendario")
            }
            Spacer(modifier = Modifier.height(height = 32.dp))
            datePickerState.selectedDateMillis?.let { dateMillis: Long ->
                Text("Fecha seleccionada: ${ dateLongToString(dateMillis,"dd/MM/yyyy" ) }")
            }
        }else{
            DatePickerDialog(
                onDismissRequest = { dialogState = false },
                confirmButton = {
                    Button(
                        onClick = { dialogState = false }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { dialogState = false }
                    ) {
                        Text(text = "Cancelar")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true
)
@Composable
fun MyDialogDatePickerExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyDialogDatePickerWithUnSelectableDaysExample()
        }
    }
}
