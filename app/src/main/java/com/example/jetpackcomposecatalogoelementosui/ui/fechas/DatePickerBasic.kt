package com.example.jetpackcomposecatalogoelementosui.ui.fechas


import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

fun dateLongToString( dateMilli: Long, pattern: String): String{

    val formatter = DateTimeFormatter.ofPattern(pattern)

    val date: LocalDateTime = Instant.ofEpochMilli(dateMilli).atZone( ZoneId.of("UTC") ).toLocalDateTime()

    val date2: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateMilli), ZoneId.systemDefault() )

    return formatter.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerExampleBasic(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //SE PRESELECCIONA LA FECHA DE ENERO 4 DEL 2020
        val datePickerState: DatePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Picker,//ACÁ INDICAMOS QUE EL MODO DE VISUALIZACIÓN INICIAL ES COMO INPUTFIELD
            initialSelectedDateMillis = 1578096000000,
        )

        DatePicker(
            state = datePickerState,
            title = {
                DatePickerDefaults.DatePickerTitle(
                    displayMode = datePickerState.displayMode,
                    modifier = Modifier.padding(all = 16.dp)
                )
            },
            dateFormatter = remember {
                    DatePickerDefaults.dateFormatter(
                    yearSelectionSkeleton = "yyyy", // Solo el año
                    selectedDateSkeleton = "dd/MM/yyyy", // Formato día/mes/año
                    selectedDateDescriptionSkeleton = "EEEE, d 'de' MMMM 'de' yyyy" // Ejemplo: Sábado, 27 de marzo de 2021
                )
            },
            modifier = Modifier
                .border(width = 2.dp, color = Color.Red)
                .padding(16.dp)
        )

        val dateSelected: String? = datePickerState.selectedDateMillis?.let {
            dateLongToString(it, "dd/MM/yyyy")
        }

        Text("Selected date timestamp: ${ dateSelected ?: "no selection"}")
    }
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
            modifier = Modifier.fillMaxSize()
        ) {
            MyDatePickerExampleBasic()
        }
    }
}