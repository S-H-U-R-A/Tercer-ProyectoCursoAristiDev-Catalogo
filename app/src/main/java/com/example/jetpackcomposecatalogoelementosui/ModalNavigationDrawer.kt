package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@Composable
fun MyModalDrawerBasic(){

    //ÁMBITO DE CORRUTINA
    val coroutineScope = rememberCoroutineScope()

    //STATE DRAWER
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val items: List<ImageVector> = listOf(
        Icons.Default.Favorite,
        Icons.Default.Face,
        Icons.Default.Email
    )

    var selectedItem by remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        scrimColor =  Color(0xFF036c3a).copy(alpha = 0.7f),
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Red,
                drawerContentColor = Color.White,
                drawerShape = CutCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
                drawerTonalElevation = 64.dp
            ) {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = (item == selectedItem),
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            selectedItem = item
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Magenta,
                            unselectedContainerColor = Color.Green
                        ),
                        modifier = Modifier
                            .padding(
                                all = 8.dp
                            )
                    )
                }
            }
        }
    ) {
        MyScaffoldExample(
            onMenuClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }
        )
    }

}




@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyModalDrawerBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyModalDrawerBasic()
        }
    }
}