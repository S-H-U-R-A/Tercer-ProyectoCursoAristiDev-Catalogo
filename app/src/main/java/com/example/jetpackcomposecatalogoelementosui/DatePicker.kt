package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
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
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerExampleBasic(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Pre-select a date for January 4, 2020
        val datePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Input,
            initialSelectedDateMillis = 1578096000000,
        )

        DatePicker(
            state = datePickerState,
            modifier = Modifier.padding(16.dp)
        )

        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialogDatePickerExampleBasic(
    modifier: Modifier = Modifier,
) {

    var showDateDialog: Boolean by remember { mutableStateOf(value = false) }

    val datePickerState = rememberDatePickerState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (!showDateDialog) {
            Button(
                onClick = { showDateDialog = true }
            ) {
                Text(text = "Show DatePicker Dialog")
            }
            if(datePickerState.selectedDateMillis != null){
                Text(
                    text = "${datePickerState.selectedDateMillis}",
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                )
            }
        } else {
            DatePickerDialog(
                onDismissRequest = {
                    showDateDialog = false
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDateDialog = false
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = {
                            showDateDialog = false
                        }
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
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
/*@ExperimentalMaterial3Api
@Composable
fun MyDialogDatePickerWithUnSelectableDays(
    modifier: Modifier = Modifier,
){
    //FECHAS A DESHABILITAR EN UNA LISTA
    val listOfDateDisable: List<String> = listOf<String>("29/12/2023", "28/12/2023")

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean { //POR CADA DIA DEL CALENDARIO DEVUELVE SU FECHA PARA VALIDARLA
                return !listOfDateDisable.contains(
                    dateLongToString(utcTimeMillis,"dd/MM/yyyy" )
                )
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year > 2022 // Si el año es mayor a 2022 esta habilitado
            }
        }
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DatePicker(state = datePickerState)

        datePickerState.selectedDateMillis?.let { dateMillis: Long ->
            Text("Selected date timestamp: ${ dateLongToString(dateMillis,"dd/MM/yyyy" ) }")
        }
    }
}*/

fun dateLongToString( dateMilli: Long, pattern: String): String{

    val formatter = DateTimeFormatter.ofPattern(pattern)

    val date: LocalDateTime = Instant.ofEpochMilli(dateMilli).atZone(ZoneId.of("UTC")).toLocalDateTime()

    val date2: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateMilli), ZoneId.systemDefault() )

    return formatter.format(date)
}







@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyDatePickerExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyDialogDatePickerExampleBasic()
        }
    }
}