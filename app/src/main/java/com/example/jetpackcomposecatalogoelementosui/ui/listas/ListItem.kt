package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyListItemExample(
    modifier: Modifier = Modifier
){
    Column {
        ListItem(
            headlineContent = { Text(text = "Cabecera de elemento de una linea") },
            leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            }
        )
        ListItem(
            headlineContent = { Text(text = "Cabecera de elemento de dos lineas") },
            supportingContent = { Text(text = "Texto secundario")},
            trailingContent = { Text("meta") } ,
            leadingContent = {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        )
        ListItem(
            headlineContent = { Text(text = "Cabecera de elemento de tres lineas") },
            overlineContent = { Text(text = "Texto sobre la linea")},
            supportingContent = { Text(text = "Texto secundario que es largo y quizas pase a otra línea.")},
            trailingContent = { Text("meta") } ,
            leadingContent = {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Mode Light"
)
@Composable
fun MyListItemPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyListItemExample()
        }
    }
}