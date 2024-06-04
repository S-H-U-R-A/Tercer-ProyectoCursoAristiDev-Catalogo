package com.example.jetpackcomposecatalogoelementosui.composition_local

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

//LA COMPOSICIÓN LOCAL ES EN POCAS PALABRAS LA FORMA DE COMPARTIR VALORES A TRAVÉS DEL ÁRBOL DE COMPOSABLES
//ESTO SIN TENER LA NECESIDAD DE HACER UN CARRETEO DEL VALOR POR CADA COMPOSABLE

//EJEMPLO DE CLASE QUE CUMPLE PARA SER USADA EN UNA COMPOSICIÓN LOCAL
data class ElevationLocal(
    val default: Dp = 0.dp,//PARA QUE SEA UN OBJETO VALIDO SE DEBE TENER UN VALOR POR DEFECTO PARA  EL CONCEPTO QUE REPRESENTA LA CLASE
    val card: Dp = 32.dp,
    val button: Dp = 8.dp
)


//ACÁ USANDO COMPOSITION LOCAL OF, INDICAMOS QUE QUEREMOS COMPARTIR ESTE OBJETO ELEVATION CON LOS COMPOSABLES
val LocalCustomElevation: ProvidableCompositionLocal<ElevationLocal> = compositionLocalOf { ElevationLocal() }


//ESTO SE HAIRÍA EN OTRO ARCHIVO, SOLO ES DE JEMPLO DE COMO USAR LOS OBJETOS PROPIOS CON COMPOSITIONLOCAL
@Composable
fun MyCompositionLocalExample() {

    val uriHandler = LocalUriHandler.current//LOCAL COMPOSITION QUE NOS AYUDA A MANEJAR LA APERTURA DE UNA URI

    var txtSaludar: String by rememberSaveable {
        mutableStateOf(value = "")
    }

    CompositionLocalProvider(//ASI ENCAPSULAMOS DENTRO DE LA LAMBDA LOS COMPOSABLES QUE PODRÁN USAR LOS OBJETOS QUE SE COMPORATEN EN EL COMPOSITION LOCAL
        values = arrayOf(
            LocalSpacing provides Spacing.default, //USANDO LA NOTACIÓN INFIX, USAMOS EL VALOR POR DEFECTO
            LocalCustomElevation.provides( ElevationLocal() )
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = LocalSpacing.current)//ACÁ USAMOS EN ESTE COMPOSABLE EL COMPOSITIONLOCAL QUE HEMOS CREADO
        ) {
            TextField(
                value = txtSaludar,
                onValueChange = {
                    txtSaludar = it
                }
            )
            Text(text = txtSaludar)
            //ESTE ES LA DESVENTAJA DE USAR COMPOSICIÓN LOCAL MUTABLE, COMO SE VE ESTAMOS CAMBIANDO EL VALOR
            //DEL OBJETO Y ESTO PUEDE SER DIFICIL DE RASTREAR DE DONDE
            CompositionLocalProvider(value = LocalSpacing provides Spacing.extraLarge ) {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = LocalCustomElevation.current.card//ACÁ USAMOS EN ESTE COMPOSABLE EL COMPOSITIONLOCAL QUE HEMOS CREADO
                    )
                ) {
                    Text(text = "Hola desde la tarjeta")
                }
                Button(
                    onClick = { uriHandler.openUri("https://www.example.com") },
                ) {
                    Text(
                        text = "Pulsar",
                        modifier = Modifier.padding(all = LocalSpacing.current)
                    )
                }

                Text(
                    text = "Esto es un texto de ejemplo",
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyCompositionLocalExamplePreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyCompositionLocalExample()
        }
    }
}