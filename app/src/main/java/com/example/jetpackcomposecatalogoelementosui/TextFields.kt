package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined() {

    var myText: String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = myText,
            onValueChange = { myText = it }, //Cada vez que cambie el texto se ejecuta esta función
            label = {
                Text(
                    text = "Introduce tu nombre" //Texto que aparece cuando no hay nada escrito
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Red, //Color del texto
                focusedBorderColor = Color.Magenta, //Color del borde cuando está enfocado
                unfocusedBorderColor = Color.Blue //Color del borde cuando no está enfocado
            ),
            modifier = Modifier.padding(24.dp)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldAdvance() {

    var myText: String by remember {
        mutableStateOf("")
    }

    TextField(
        value = myText,
        onValueChange = {//Cada vez que cambie el texto se ejecuta esta función
            //Esto es una muestra de que podemos ejecutar lógica dentro de la función lambda
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
        label = {
            Text(
                text = "Introduce tu nombre" //Texto que aparece cuando no hay nada escrito
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    name: String,
    onNameChange: (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) }, //Cada vez que cambie el texto se ejecuta esta función
    )

}