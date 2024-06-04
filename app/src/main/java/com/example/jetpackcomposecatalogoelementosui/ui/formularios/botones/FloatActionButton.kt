package com.example.jetpackcomposecatalogoelementosui.ui.formularios.botones

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class TypeFAB{
    NORMAL,
    SMALL,
    LARGE,
    EXTENDED_BASIC,
    EXTENDED_CUSTOM
}

@Composable
fun MySimpleExampleScaffoldForFAB(typeFAB: TypeFAB){

    val listState: LazyListState = rememberLazyListState()

    val scope: CoroutineScope = rememberCoroutineScope()

    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        floatingActionButton = {
            when(typeFAB){
                TypeFAB.NORMAL -> MyFABBasic(onFABClick = {})
                TypeFAB.SMALL -> MyFABSmallExample()
                TypeFAB.LARGE -> MyFABLargeExample()
                TypeFAB.EXTENDED_BASIC -> {
                    MyFABExtendedBasicExample(
                        expandedFab = expandedFab,
                        stateList = listState,
                        scope = scope
                    )
                }
                TypeFAB.EXTENDED_CUSTOM -> MyFABExtendedCustomExample()
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            for (index in 0 until 100) {
                item {
                    Text(
                        text = "List item - $index",
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MyFABBasic(
    onFABClick: () -> Unit
){
    FloatingActionButton(
        onClick = { onFABClick() },
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar"
        )
    }
}

@Composable
fun MyFABSmallExample(
    modifier: Modifier = Modifier
){
    SmallFloatingActionButton(
        shape = FloatingActionButtonDefaults.extendedFabShape,
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar"
        )
    }
}


@Composable
fun MyFABLargeExample(
    modifier: Modifier = Modifier
){
    LargeFloatingActionButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar"
        )
    }
}

@Composable
fun MyFABExtendedBasicExample(
    expandedFab: Boolean,
    stateList: LazyListState,
    scope: CoroutineScope
){
    ExtendedFloatingActionButton(
        expanded = expandedFab,
        text = { Text(text = "Subir") },
        icon = { Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "") },
        onClick = {
            scope.launch {
                stateList.scrollToItem(0)
            }
        }
    )
}


//Esta sobrecarga se usa más para un botón extendido de solo texto
@Composable
fun MyFABExtendedCustomExample(){
    ExtendedFloatingActionButton(
        onClick = { }
    ) {
        Text(text = "Darlyn")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyExampleFAB(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySimpleExampleScaffoldForFAB(TypeFAB.EXTENDED_CUSTOM)
        }
    }
}