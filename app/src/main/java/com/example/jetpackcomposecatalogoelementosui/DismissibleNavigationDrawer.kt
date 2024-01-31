package com.example.jetpackcomposecatalogoelementosui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@Composable
fun MyDismissibleNavigationDrawerBasic(
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val items: List<ImageVector> = listOf(
        Icons.Default.Favorite,
        Icons.Default.Face,
        Icons.Default.Email
    )

    var selectedItem by remember { mutableStateOf(items[0]) }

    BackHandler( //ESTO SE USA PARA QUE CUANDO SE PULSE A TRÁS FISICAMENTE EL DRAWER SE CIERRE
        enabled = drawerState.isOpen
    ) {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    DismissibleNavigationDrawer(
        modifier = Modifier.background(Color.Magenta),
        gesturesEnabled = true,
        drawerState = drawerState,
        drawerContent = {
            DismissibleDrawerSheet(
                drawerContainerColor = Color.Red,
                drawerContentColor = Color.Transparent,
                drawerShape = CutCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
                drawerTonalElevation = 64.dp
            ) {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            selectedItem = item
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { coroutineScope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
            }
        }
    )
}

@Preview
@Composable
fun MyDismissibleNavigationDrawerBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyDismissibleNavigationDrawerBasic()
        }
    }
}