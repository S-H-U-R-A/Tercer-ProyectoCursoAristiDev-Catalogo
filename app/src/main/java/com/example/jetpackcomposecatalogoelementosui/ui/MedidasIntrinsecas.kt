package com.example.jetpackcomposecatalogoelementosui.ui

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


//ESTE CONCEPTO CONSISTE EN OBTENER LAS MEDIDAS DEL LOS COMPOSABLES HIJOS DE UN COMPOSABLE PADRE
//ESTAS MEDIDAS DE LOS COMPOSABLES HIJOS SE OBTIENEN ANTES DE ASIGNARLE AL PADRE UN TAMAÑO ES DECIR EN LA FASE
@Composable
fun MyIntrinsicExample(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            //ACÁ INDICAMOS QUE LA ALTURA DEL PADRE EN ESTE CASO LA FILA SERÁ LA ALTURA DE SU COMPOSABLE HIJO CON MÁS ALTURA
            .height(intrinsicSize = IntrinsicSize.Max)
            .border(0.5.dp, Color.Red)//A pesar de que se pone el modificador en el padre, esto es una indicación para los hijos
    ) {

        Text(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue)
                .weight(weight = 1f)
                .padding(start = 4.dp)
                .wrapContentWidth(align = Alignment.Start),//ESTA ALINEACIÓN NO TIENE SENTIDO SI NO SE USAN LOS PESOS
            text = "Hi"
        )

        VerticalDivider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
        )

        Text(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(end = 4.dp)
                .wrapContentWidth(align = Alignment.End),//ESTA ALINEACIÓN NO TIENE SENTIDO SI NO SE USAN LOS PESOS
            text = "Composable hijo con más altura, por ende la fila tendra la altura de este por medió de la altura IntrinsicSize.Min"
        )

    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyIntrinsicPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            MyIntrinsicExample()
        }
    }
}