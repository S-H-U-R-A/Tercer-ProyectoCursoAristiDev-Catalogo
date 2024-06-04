package com.example.jetpackcomposecatalogoelementosui.ui.interacciones_y_eventos


import android.content.res.Configuration
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlin.math.roundToInt


enum class DragAnchors{ START, CENTER, END }//DEFINIMOS LOS TIPOS DE ESTADOS QUE REPRESENTAN LAS ANCLAS EN DONDE PUEDE PONERSE EL COMPOSABLE QUE SE DESLICE


//EL FUNCIONAMIENTO DE SWIPEABLE, HACE NECESARIO UN CONTENEDOR QUE LLEVARÁ LAS ANCLAS Y OTRO QUE SERÁ EL QUE SE PUEDE ARRASTRAR
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyAnchoredDraggableExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){
    val density: Density = LocalDensity.current//OBTENEMOS LA DENSIDAD DE PANTALLA

    //ESTADO QUE PERMITE CONFIGURAR COMO VA FUNCIONAR EL ARRASTRE Y CUALES SERÁN LAS ANCLAS PARA PARAR
    val anchoredDraggableState = remember {
        AnchoredDraggableState(//SE CREA UN OBJETO DE LA CLASE QUE PERMITE CONFIGURAR LOS ASPECTOS DE LAS ANCLAS
            anchors = DraggableAnchors<DragAnchors> {
                //LA CONVERSIÓN DE DP A PX SE PUEDE HACER EN EL CONTEXTO DE LA DENSIDAD DE PANTALLA
                DragAnchors.START at with(density){//ASOCIAMOS EL ESTADO START CON LA MEDIDA -100 POR MEDIO DEL MÉTODO DE EXTENCIÓN INFIX AT()
                    -180.dp.toPx()
                }
                DragAnchors.CENTER.at(0f)//ACÁ USAMOS EL MÉTODO DE EXTENSIÓN AT() COMO SE USARIA NORMALMENTE
                DragAnchors.END at with(density){//ASOCIAMOS EL ESTADO START CON LA MEDIDA 100 POR MEDIO DEL MÉTODO DE EXTENCIÓN INFIX AT()
                    180.dp.toPx()
                }
            },
            initialValue = DragAnchors.CENTER,//ESTADO INICIAL CPN EL QUE INICIARIA EL COMPOSABLE
            positionalThreshold = { distanciaTotal: Float ->//DISTANCIA-TOTAL: ES LA DISTANCIA TOTAL ENTRE UN PUNTO DE ANCLAJE Y OTRO, ESTOS ANCLAJES SON DEFINIDOS EN EL ENUM CLASS
                //A PARTIR DE LA DISTANCIA-TOTAL PODEMOS CALCULAR A CUANTA DISTANCIA QUEREMOS QUE SE ACTIVE EL TIRON HACIA UNA DE LAS ANCLAS
                distanciaTotal * 0.5f//EN ESTE CASO ESTA OPERACIÓN OBTENEMOS COMO RESULTADO LA MITAD DE LA DISTANCIA TOTAL, ENTONCES INDICAMOS QUE DESDE EL 50% SE DETECTE EL TIRÓN DE LAS ANCALS EN CUALQUIERA DE LOS DOS SENTIDOS
            },
            velocityThreshold = {//ACÁ SE CONFIGURA LA VELOCIDAD CON LA QUE TAMBIEN DETECTAREMOS SI SE DESPLAZA DE UN ANCLA A LA OTRA, ESTA VELOCIDAD TIENE MÁS PRIORIDAD QUE positionalTresHold
                with(density) {
                    50.dp.toPx()//SI EL EVENTO DE ARRASTRE HECHO POR EL USUARIO SUPERA LOS 50.DP, SE ACTIVARÁ EL TIRON HACIA EL ANCLA EN EL SENTIDO DEL ARRASTRE, ES DECIR HACIA LA IZQUIERDA O LA DERECHA.
                }
            },
            animationSpec = spring(),//ANIMACIÓN QUE VA USAR EL COMPOSABLE DESPLAZABLE
            confirmValueChange = { objectDragAnchors: DragAnchors ->
                when(objectDragAnchors){//COMO SUCEDE EN EL BOTTOMSHEET, ACÁ PODEMOS OBTNER CADA EVENTO Y DECIDIR SI IGNORAMOS EL EVENTO
                    DragAnchors.START -> {true}
                    DragAnchors.CENTER -> {false}
                    DragAnchors.END -> {true}
                }
            }
        ).apply {
            //ESTA ES OTRA FORMA DE DEFINIR LAS ANCLAS, ES DECIR SE PUEDE DEFINIR EN EL STADO O DE ESTA FORMA
            updateAnchors(
                newAnchors = DraggableAnchors{//Map<String - Son las Llaves, Float - Son los Píxeles>
                    DragAnchors.START at 0f
                    DragAnchors.CENTER at 450f
                    DragAnchors.END at 900f
                }
            )
        }
    }

    Box(//ESTE COMPOSABLE ACTURÁ COMO EL CONTENEDOR DE LAS ANCLAS EN DONDE EL ELEMENTO DESLIZABLE SE MOVERÁ
        modifier = modifier
            .windowInsetsPadding(insets = WindowInsets.safeGestures)
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.flash),
            modifier = Modifier
                .size(size= 50.dp)
                .border(color = Color.Red, width = 1.dp)
                .offset {//POSICIÓN DE LA IMÁGEN
                    //A PARTIR DEL ESTADO OBTENEMOS LA DISTANCIA DEL DESPLAZAMIENTO EN LA HORIENTACIÓN CONFIGURADA, PARA ESTE CASO EN HORIZONTAL
                    IntOffset(
                        x = anchoredDraggableState.requireOffset().roundToInt(),//EL COMPOSABLE SE VA A DESPLAZAR  CON BASE EN EL ESTADO Y EL OFFSET O MOVIMIENTO QUE ESTE DEVULEVE
                        y = 0
                    )
                }
                .clip(shape = CircleShape)
                .anchoredDraggable(//SE AÑADE LA CAPACIDAD DE SER ARRASTRABLE A ESTE COMPOSABLE
                    anchoredDraggableState,//HACEMOS MATCH CON EL ESTADO QUE CREAMOS
                    Orientation.Horizontal//CONFIGURAMOS LA HORIENTACIÓN DEL DESLIZABLE
                ),
            contentDescription = null,
        )
    }
}



@Preview(
    name = "Examples of Draggable, Swiping and Fling",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyEventsExamplesPrevie(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyAnchoredDraggableExample()
        }
    }
}