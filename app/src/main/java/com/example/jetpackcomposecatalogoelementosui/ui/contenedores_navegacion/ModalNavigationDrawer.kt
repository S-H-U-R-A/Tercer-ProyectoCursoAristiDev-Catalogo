package com.example.jetpackcomposecatalogoelementosui.ui.contenedores_navegacion

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
import com.example.jetpackcomposecatalogoelementosui.ui.contenedores.MyScaffoldExample
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@Composable
fun MyModalDrawerBasic(){
    val coroutineScope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA

    val drawerState = rememberDrawerState(//ESTADO DEL MODAL NAVIGATION DRAWER QUE PERMITE CONFIGURAR Y OBTENER INFORMACIÓN DE LOS ESTADOS QUE PUEDE TENER EL DRAWER
        initialValue = DrawerValue.Closed,
        confirmStateChange = {//IGUAL A COMO FUNCIONA EN EL MODAL BOTTOMSHEET, ESTA LAMBDA NOS PERMITE TENER MÁS CONTROL SOBRE LOS ESTADOS
            true
        }
    )

    val items: List<ImageVector> = listOf(//LISTADO DE EJEMPLO PARA LOS ITEMS DE EL MODAL NAVIGATION DRAWER LATERAL
        Icons.Default.Favorite,
        Icons.Default.Face,
        Icons.Default.Email
    )

    var selectedItem by remember { mutableStateOf(items[0]) }//AL IGUAL QUE EL LA BARRA DE NAVEGACIÓN SIMPLE, ESTE ESTADO LO USAMOS PARA DETERMINAR CUAL ITEM ESTA SELECCIONADO

    ModalNavigationDrawer(
        scrimColor =  Color(0xFF036c3a).copy(alpha = 0.7f),//COLOR DEL FONDO QUE SE PONE DETRAS DEL MODAL CUANDO ESTE APRECE
        drawerState = drawerState,//ESTADO CON EL QUE TRABAJA ESTE ENVOLTORIO DEL DRAWER
        gesturesEnabled = true,//HABILITAR O INHABILITAR LOS GESTOS PARA CERRAR O ABRIR LA HOJA LATERAL
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Red,//DEFINICIÓN DE ESTILOS DE LA HOJA LATERAL
                drawerContentColor = Color.White,
                drawerShape = CutCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
                drawerTonalElevation = 64.dp
            ) {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->//RECORREMOS LA LISTA DE ELEMENTOS Y DIBUJAMOS CADA ITEM
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = (item == selectedItem),//SI EL INDICE DEL ITEM EN LA LISTA ES IGUAL AL ESTADO DE SELECCIONADO, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
                        onClick = {
                            coroutineScope.launch { drawerState.close() }//CUANDO PULSAMOS UN ELEMENTO CERRAMOS LA HOJA LATERAL
                            selectedItem = item//MARCAMOS COMO SELECCIONADO EL ITEM QUE SE PULSO
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
        MyScaffoldExample(//ESTE SERÍA EL SCAFFOLD
            onMenuClick = {
                coroutineScope.launch {
                    drawerState.open()//CAMBIAMOS EL ESTADO DE LA HOJA LATERAL HA ABIERTO PARA QUE SE MUESTRE
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