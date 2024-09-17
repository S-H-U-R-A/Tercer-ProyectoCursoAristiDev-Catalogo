package com.example.jetpackcomposecatalogoelementosui.composition_local

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//LA COMPOSICIÓN LOCAL ES EN POCAS PALABRAS ES LA FORMA DE COMPARTIR VALORES A TRAVÉS DEL ÁRBOL DE COMPOSABLES
//ESTO SIN TENER LA NECESIDAD DE HACER UN CARRETEO DEL VALOR POR CADA COMPOSABLE


//EJEMPLO DE OBJETO QUE CUMPLE PARA SER USADO EN UNA COMPOSICIÓN LOCAL, ESTA ES OTRA FORMA DE USARLA
//EN LA CUAL PODEMOS CAMBIAR EL VALOR, AUNQUE ESTO GENERA UNAS DESVENTAJAS, SINEMBARGO ES IMPORTANTE TENER EL EJEMPLO
object Spacing{
    val default: Dp = 0.dp//PARA QUE SEA UN OBJETO VALIDO SE DEBE TENER UN VALOR POR DEFECTO PARA  EL CONCEPTO QUE REPRESENTA LA CLASE
    val extraSmall: Dp = 4.dp
    val small: Dp = 8.dp
    val medium: Dp = 16.dp
    val large: Dp = 32.dp
    val extraLarge: Dp = 64.dp
}

//ACÁ USANDO COMPOSITION LOCAL OF, INDICAMOS QUE QUEREMOS COMPARTIR ESTE OBJETO SPACING CON LOS COMPOSABLES
val LocalSpacing: ProvidableCompositionLocal<Dp> = compositionLocalOf { Spacing.default } //OBJETO DE COMPOSICIÓN LOCAL PARA COMPARTIR UN TIPO DE RELLENO
