package com.example.jetpackcomposecatalogoelementosui.ui.contenedores_navegacion

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
    val coroutineScope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA
    //ESTADO DEL MODAL NAVIGATION DRAWER QUE PERMITE CONFIGURAR Y OBTENER INFORMACIÓN DE LOS ESTADOS QUE PUEDE TENER EL DRAWER
    val drawerState = rememberDrawerState(//ESTADO DEL MODAL NAVIGATION DRAWER DISMISSIBLE QUE PERMITE CONFIGURAR Y OBTENER INFORMACIÓN DE LOS ESTADOS QUE PUEDE TENER EL DRAWER
        initialValue = DrawerValue.Closed,
        confirmStateChange = {//IGUAL A COMO FUNCIONA EN EL MODAL BOTTOMSHEET, ESTA LAMBDA NOS PERMITE TENER MÁS CONTROL SOBRE LOS ESTADOS
            true
        }
    )
    //LISTADO DE EJEMPLO PARA LOS ITEMS DE EL MODAL NAVIGATION DRAWER LATERAL
    val items: List<ImageVector> = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)

    var selectedItem by remember { mutableStateOf(items[0]) }//AL IGUAL QUE EL LA BARRA DE NAVEGACIÓN SIMPLE, ESTE ESTADO LO USAMOS PARA DETERMINAR CUAL ITEM ESTA SELECCIONADO

    BackHandler(//ESTO SE USA PARA QUE CUANDO SE PULSE A TRÁS FISICAMENTE EL DRAWER SE CIERRE
        enabled = drawerState.isOpen//SI EL DRAWER ESTA ABIERTO, ENTONCES ESTE COMPOSABLE MANEJARÁ EL EVENTO DEL BOTÓN FISICO ATRÁS
    ) {
        coroutineScope.launch {
            drawerState.close()//SI EL USUARIO PULSA EL BOTÓN ATRÁS Y LA HOJA LATERAL ESTA ABIERTA ENTONCES SE CERRARÁ LA HOJA LATERAL.
        }
    }

    DismissibleNavigationDrawer(
        modifier = Modifier.background(Color.Magenta),
        gesturesEnabled = true,//HABILITAR O INHABILITAR LOS GESTOS PARA CERRAR O ABRIR LA HOJA LATERAL
        drawerState = drawerState,//ESTADO CON EL QUE TRABAJA ESTE ENVOLTORIO DEL DRAWER
        drawerContent = {
            DismissibleDrawerSheet(
                drawerContainerColor = Color.Red,//DEFINICIÓN DE ESTILOS DE LA HOJA LATERAL
                drawerContentColor = Color.Transparent,
                drawerShape = CutCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
                drawerTonalElevation = 64.dp
            ) {
                Spacer(Modifier.height(24.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = (item == selectedItem),//SI EL INDICE DEL ITEM EN LA LISTA ES IGUAL AL ESTADO DE SELECCIONADO, ENTONCES ESTE ELEMENTO DEBERÍA ESTAR SELECCIONADO
                        onClick = {
                            coroutineScope.launch { drawerState.close() }//CUANDO PULSAMOS UN ELEMENTO CERRAMOS LA HOJA LATERAL
                            selectedItem = item//MARCAMOS COMO SELECCIONADO EL ITEM QUE SE PULSO
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
                Button(onClick = { coroutineScope.launch { drawerState.open() } }) {//CUANDO SE PULSE ESTE BOTÓN SE MOSTRARÁ LA HOJA LATERAL
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