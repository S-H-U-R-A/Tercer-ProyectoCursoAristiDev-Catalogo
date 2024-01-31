package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyPermanentNavigationDrawerBasic(
    modifier: Modifier = Modifier
){
    val items: List<ImageVector> = listOf(
        Icons.Default.Favorite,
        Icons.Default.Face,
        Icons.Default.Email
    )

    var selectedItem by remember {
        mutableStateOf(items[0])
    }

    PermanentNavigationDrawer(
        modifier = Modifier.background(Color.White),
        drawerContent = {
            PermanentDrawerSheet(
                drawerTonalElevation = 0.dp,
                drawerContainerColor = Color.Red,
                drawerContentColor = Color.White,
                drawerShape = CutCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
            ) {
                Spacer(Modifier.height(12.dp))
                items.forEach{ item: ImageVector ->
                    NavigationDrawerItem(
                        icon = {
                           Icon(
                               imageVector = item,
                               contentDescription = null
                           )
                        },
                        label = { Text(text = item.name) },
                        selected = (item == selectedItem),
                        onClick = { selectedItem = item },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text( color = Color.Black, text = "Contenido de la aplicación" )
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyPermanentNavigationDrawerBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyPermanentNavigationDrawerBasic()
        }
    }
}