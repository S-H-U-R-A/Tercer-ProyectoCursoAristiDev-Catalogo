package com.example.jetpackcomposecatalogoelementosui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyBasicSlider() {

    var sliderPosition: Float by rememberSaveable { mutableStateOf(0f) } //Estado inicial del slider

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            }, //Cuando cambia el valor del slider, se actualiza el estado
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Slider position: $sliderPosition"
        )
    }

}

@Composable
fun MyAdvanceSliderExample() {

    var sliderPosition: Float by rememberSaveable { mutableStateOf(0f) } //Estado inicial del slider


    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { currentValue: Float ->
                sliderPosition =
                    currentValue //Cuando cambia el valor del slider, se actualiza el estado
            },
            onValueChangeFinished = {}, //Esta lambda se ejecuta cuando el slider para en alguno de los Step definidos
            valueRange = 0f..10f, //Rango de valores del slider,
            steps = 9, //Pasos del slider, Siempre hay que restarles 1 al valor que se quiere
            enabled = true,
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.Blue,
                activeTickColor = Color.White,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Slider position: ${sliderPosition.toInt()}" //Si queremos guardar este valor como entero, debemos crear otro estado
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSliderExample() {
    
    var currentRange: ClosedFloatingPointRange<Float> by remember {
        mutableStateOf(0f..2f) 
    } //Estado inicial del slider

    Column(
        modifier = Modifier
            .padding( all = 16.dp)
    ) {
        RangeSlider(
            value = currentRange,
            valueRange = 0f..10f,
            onValueChange = { currentRangeSelected: ClosedFloatingPointRange<Float> ->
                currentRange = currentRangeSelected
            },
            steps = 9
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Valor inicial: ${currentRange.start}"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Valor Final: ${currentRange.endInclusive}"
        )
    }
}