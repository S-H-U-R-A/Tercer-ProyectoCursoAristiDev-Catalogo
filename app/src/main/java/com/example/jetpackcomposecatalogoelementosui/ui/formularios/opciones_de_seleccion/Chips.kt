package com.example.jetpackcomposecatalogoelementosui.ui.formularios.opciones_de_seleccion

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MyElevatedSuggestionChip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        ElevatedSuggestionChip(
            onClick = {},
            label = { Text(text = "Elevated Suggestion Chip") },
            colors = SuggestionChipDefaults.suggestionChipColors()
        )
    }
}

@Composable
fun MySuggestionChip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        SuggestionChip(
            onClick = {},
            label = { Text(text = "Elevated Suggestion Chip") },
            icon = {
                   Icon(imageVector = Icons.Default.Done, contentDescription = null)
            },
            colors = SuggestionChipDefaults.suggestionChipColors(
                containerColor = Color.LightGray,
                iconContentColor = Color.White
            )
        )
    }
}

@Composable
fun MyElevatedFilterChip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    var selected: Boolean by remember { mutableStateOf(false)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        ElevatedFilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text(text = "Filter Chip") },
            leadingIcon = {
                if(selected){
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }else{
                    null
                }
            }
        )
    }
}

@Composable
fun MyFilterChip(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    var selectedFilter: Boolean by remember { mutableStateOf(false) }//ESTADO PARA MARCAR O DESMARCAR EL CHIP COMO SELECCIONADO O NO

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        FilterChip(
            selected = selectedFilter,
            onClick = { selectedFilter = !selectedFilter },
            label = { Text(text = "Filtro palabras por la letra A") },
            leadingIcon = {
                if(selectedFilter){
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }else{
                    null
                }
            }
        )
    }
}

@Composable
fun MyChipElevatedAssistExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    val context: Context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        ElevatedAssistChip(
            onClick = { Toast.makeText(context, "Pulsado Asistente", Toast.LENGTH_SHORT).show() },
            label = { Text(text = "Asistente") },
            elevation = AssistChipDefaults.assistChipElevation(elevation = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Descripción",
                    modifier = Modifier.size(size = AssistChipDefaults.IconSize)
                )
            }
        )
    }
}

@Composable
fun MyChipAssistExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    val context: Context = LocalContext.current//CONTEXTO

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        AssistChip(
            onClick = { Toast.makeText(context, "Pulsado Asistente", Toast.LENGTH_SHORT).show() },
            label = { Text(text = "Asistente") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Descripción",
                    modifier = Modifier.size(size = AssistChipDefaults.IconSize)
                )
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyInputChipExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    val seleccionado by remember { mutableStateOf(false)}

    val listChips: SnapshotStateList<String> = remember { mutableStateListOf() }//LISTA DE PALABRAS GUARDADAS POR EL SUSUARIO

    var inputState by remember {mutableStateOf("")}//ESTADO PARA EL TEXTFIELD

    val keyBoardController = LocalSoftwareKeyboardController.current//CONTROLADOR O MANEJADOR DEL TECLADO

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = inputState,
            onValueChange = { textoIngresado: String ->
                inputState = textoIngresado
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {//CUANDO SE PULSE EL BOTÓN DE HECHO
                    if( inputState.isNotEmpty() ){//VALIDAMOS SI EL CAMPO DE TEXTO NO ESTA VACIO Y AÑADIMOS LA PALABRA O FRASE A LA LISTA DE PALABRAS
                        listChips.add(element = inputState.trim())
                    }
                    inputState = ""//RESETEAMOS EL VALOR DE TEXTFIELD
                    keyBoardController?.hide()//OCULTAMOS EL TECLADO
                }
            ),
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlowRow(//FILA AJUSTABLE EN DONDE COLOCAREMOS CADA FRASE O PALABRA QUE ESCRIBA EL USUARIO
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            listChips.forEachIndexed { index: Int, textChip: String ->//RECORREMOS LA LISTA DE FRASE O PALABRAS Y DIBUJAMOS CADA UNA EN UN ELEMENTO CHIP
                InputChip(
                    selected = seleccionado ,
                    onClick = { listChips.removeAt(index) },//CUANDO HACEMOS CLICK EN UN ELEMENTO CHIP LO REMOVEMOS DE LA LISTA
                    label = { Text(text = textChip) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = null,
                        )
                    }
                )
            }
        }
    }
}




@Preview(
    name = "EEjemplos básicos de chips",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyChipsExamplesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MySuggestionChip()
        }
    }
}