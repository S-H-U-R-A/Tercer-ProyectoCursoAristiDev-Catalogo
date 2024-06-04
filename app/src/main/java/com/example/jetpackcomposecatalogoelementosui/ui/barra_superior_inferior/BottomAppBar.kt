package com.example.jetpackcomposecatalogoelementosui.ui.barra_superior_inferior

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySampleScaffoldForBottomAppBarExamples()   {
    val stateTest: BottomAppBarState = rememberBottomAppBarState()

    //PERMITE DEFINIR EL COMPORTAMIENTO DE LA BARRA CUANDO LA PANTALLA SE PUEDE DESPLAZAR, POR EJEMPLO CON LAS LISTAS
    val scrollBehavior: BottomAppBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(state = stateTest)

    Scaffold(
        bottomBar = { MyCustomBottomAppBarOverload(scrollBehavior = scrollBehavior) },
        modifier = Modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection)
    ) { paddingValues: PaddingValues ->
        Column {
            Text(text = "${stateTest.heightOffset}")
            LazyColumn(
                    contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
            ) {
                items(200){
                    Text(text = "Te amo Darlyn")
                }
            }
        }
    }
}


@Composable
fun MySimpleBottomAppBar(){
    BottomAppBar(
        actions = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
                Text(text = "Te amo darlyn")
            }
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Red,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check"
                )
            }
        }
    )
}


/**
 * My custom bottom app bar esta es la versión de usar la sobrecarga más personalizable
 *
 *
 * @param modifier
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomBottomAppBarOverload(
    modifier: Modifier = Modifier,
    scrollBehavior: BottomAppBarScrollBehavior
) {
    BottomAppBar(
        containerColor = Color.Red,
        contentColor = Color.White,
        tonalElevation = 64.dp,
        scrollBehavior = scrollBehavior
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Account"
            )
        }
        FloatingActionButton(
            onClick = { },
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check"
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyBottomAppBarBasicExamplesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MySampleScaffoldForBottomAppBarExamples()
        }
    }
}
