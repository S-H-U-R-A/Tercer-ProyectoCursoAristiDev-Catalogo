package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateRangePickerExampleBasic(
    modifier: Modifier = Modifier
){

    var showDialog by remember { mutableStateOf(value = false) }

    val dateRangePickerState = rememberDateRangePickerState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Button(
            onClick = { showDialog = true }
        ) {
            Text(text = "Mostrar dialog de rango de fechas")
        }

        if(showDialog){
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = { 
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showDialog = false }) {
                        Text(text = "Cancelar")
                    }
                },
                modifier = Modifier
            ) {
                DateRangePicker(
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp)
                        ) {
                            Text(
                                text = "Seleccione un rango de fechas"
                            )
                        }
                    },
                    showModeToggle = true,
                    headline = {
                       Row(
                           horizontalArrangement = Arrangement.SpaceAround,
                           modifier = Modifier
                               .fillMaxWidth()
                       ) {
                           Text(
                               text = if(dateRangePickerState.selectedStartDateMillis != null){
                                   dateLongToString(dateRangePickerState.selectedStartDateMillis!!, "dd/MM/yyyy")
                               }else{"dd/mm/yyyy"},
                           )
                           Text(text = "a")
                           Text(
                               text = if(dateRangePickerState.selectedEndDateMillis != null){
                                   dateLongToString(dateRangePickerState.selectedEndDateMillis!!, "dd/MM/yyyy")
                               }else{"dd/mm/yyyy"},
                           )
                       }
                    },
                    state = dateRangePickerState,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        if(dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null){
            Text(
                text = "${dateRangePickerState.selectedStartDateMillis} ${dateRangePickerState.selectedEndDateMillis}",
                modifier = Modifier.align(alignment = Alignment.BottomCenter)
            )
        }
    }
}






@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyDateRangePickerPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyDateRangePickerExampleBasic()
        }
    }
}