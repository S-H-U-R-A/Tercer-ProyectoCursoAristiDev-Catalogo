package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyCustomLayout(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
) {
    MyBasicColumn(
        modifier = modifier
            .padding(all = 8.dp)
    ) {
        Text("MyBasicColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}


//EJEMPLO DE CREAR UN COLUMN DESDE CERO
@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Layout(
        modifier = modifier,
        content = content
    ){
        composablesMedibles: List<Measurable>, //Lista de composables hijos que se pueden medir
        constraints: Constraints -> //Restriciones de altura y anchura del padre o contenedor donde se van a ubicar los composables hijos
        
        val colocables: List<Placeable> = composablesMedibles.map { composableMedible: Measurable -> //Recorremos la lista de composables hijos
            composableMedible
                .measure( //Acá indicamos las restriciones del padre para cada composable hijo y nos retorna un objeto colocable que ya se puede posicionar en X e Y
                    constraints = constraints
                )
        }

        layout(//El contenedor tendra las dimesiones del padre
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ){

            var posicionEnY = 0 //Posición en Y de cada composable a dibujar

            colocables.forEach{ colocable: Placeable ->

                colocable.placeRelative(x= 0, y= posicionEnY) // Ubicamos el colocable

                posicionEnY += colocable.height // Aumentamos el acumulador en altura en Y

            }
        }
    }
}





//MODIFICADOR PERSONALIZADO DE CUSTOM LAYOUT

/**
 * First baseline to top
 *
 * @param firstBaselineToTop
 */
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable: Measurable, constraints: Constraints ->  // Medible será el composable al cual se aplica
                            //Medible/Estimable - medidas
    val placeable: Placeable = measurable.measure(constraints)

    //VERIFICAMOS SI EL COMPOSABLE MEDIBLE TIENE UNA LINEA BASE Y LA OBTENEMOS
    check( placeable[FirstBaseline] != AlignmentLine.Unspecified)//Si el valor es falso se lanza un error
    val firstBaseline: Int = placeable[FirstBaseline]//Obtenemos la lina base

    //CALCULAMOS LA ALTURA PARA EL COMPOSABLE RESTANDO LA ALTURA DE LA LINEA BASE
    val positionY: Int = firstBaselineToTop.roundToPx() - firstBaseline
    val altura: Int = placeable.height + positionY

    //UBICAMOS EL COMPOSABLE
    layout(
        width = placeable.width, //ALTURA Y ANCHURA PARA EL COMPOSABLE
        height = altura
    ){
        //UBICAMOS EL COMPOSABLE
        placeable.placeRelative(x= 0, y= positionY)
    }
} 


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyCustomLayoutPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyCustomLayout()
        }
    }
}