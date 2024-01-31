package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

//USADO PARA TABLETS Y ESCRITORIO
@Composable
fun MyNavigationRailExampleBasic(
    modifier: Modifier = Modifier
){

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val items = listOf("Home", "Search", "Settings")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    Scaffold {
        NavigationRail(
            header = {
                FloatingActionButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                }
            },
            containerColor = Color.White,
            contentColor = Color.Blue,
            modifier = modifier
                .padding(paddingValues = it)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = (selectedItem == index),
                    onClick = { selectedItem = index },
                    icon = { Icon(imageVector = icons[index] , contentDescription = item) },
                    label = { Text(text = item) },
                    alwaysShowLabel = true,
                    colors = NavigationRailItemDefaults.colors(
                        selectedTextColor = Color.Red
                    )
                )
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Modo Oscuro"
)
@Composable
fun MyNavigationRailExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyNavigationRailExampleBasic()
        }
    }
}