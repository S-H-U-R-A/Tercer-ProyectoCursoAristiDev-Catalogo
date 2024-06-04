package com.example.jetpackcomposecatalogoelementosui.ui.formularios.botones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMultiChoiceSegmentedButtonExample(
    modifier: Modifier = Modifier
){
    //ESTADO QUE ALMACENA LAS OPCIONES SELECCIONADAS
    val checkedList = remember { mutableStateListOf<Int>() }

    val options = listOf("Favorites", "Trending", "Saved")

    val icons = listOf(Icons.Filled.StarBorder, Icons.AutoMirrored.Filled.TrendingUp, Icons.Filled.BookmarkBorder)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MultiChoiceSegmentedButtonRow(
            modifier = Modifier
        ) {
            options.forEachIndexed { indice: Int, item: String ->
                SegmentedButton(
                    checked = (indice in checkedList),// Si la opción está en la lista de seleccionados lo marca como seleccionado
                    onCheckedChange = {
                        if(indice in checkedList){ checkedList.remove(indice) }else{ checkedList.add(indice) }
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = indice,
                        count = options.size
                    ),
                    icon = {
                        SegmentedButtonDefaults.Icon(
                            active = (indice in checkedList),
                            inactiveContent = {
                                Icon(
                                    imageVector = icons[indice],
                                    contentDescription = null,
                                    modifier = Modifier.size(size = SegmentedButtonDefaults.IconSize)
                                )
                            }
                        )
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun MyMultiChoiceSegmentedButtonExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyMultiChoiceSegmentedButtonExample()
        }
    }
}
