package com.example.jetpackcomposecatalogoelementosui.ui.graficos

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


//PARA AJUSTAR EL TAMAÑO DEL CANVAS ES NECESARIO PONERLO DENTRO DE UN COMPOSABLE CONTENEDOR
//DE LO CONTRARIO OCUPARÁ EL TOTAL DEL ESPACIO DE LA PANTALLA

//CUANDO EN ESTOS EJEMPLOS USO THIS ES PARA QUE SE VEA QUE LAS PROPIEDADES O MÉTODOS
//VIENEN DADAS POR EL ALCANCE DE DRAWSCOPE

@Composable
fun MiEjemploDeTransformacionDeEscalaOTamanio(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ.
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        //ACÁ INDICAMOS QUE LA FIGURA QUE DIBUJEMOS DENTRO DE ESTA TRANSFORMACIÓN, DEBEMOS AMPLIARLA EN TAMAÑO 20 VECES POR X Y 10 VECES POR Y
        this.scale(scaleX = 20f, scaleY = 10f) {
            this.drawCircle(//EN ESTA PARTE DIBUJAMOS UN CIRCULO
                color = Color.Blue,
                radius = 20f,//TAMAÑO DEL CIRCULO
                center = center//UBICACIÓN DEL CENTRO DEL CIRCULO EN EL LIENZO.
            )
        }
    }
}

@Composable
fun MiEjemploDeTransformacionDeTraslacionOPosicion(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ.
    Canvas( // TRANSLADAR UN DIBUJO EN CUALQUIERA DE LOS CUATRO SENTIDOS
        modifier = Modifier.fillMaxSize()
    ) {
        //ESTA TANSFORMACIÓN PERMITE MOVER LA FIGURA EN CUALQUIERA DE LOS CUATRO SENTIDOS [DERECHA, ARRIBA, IZQUIERDA, ABAJO]
        //LA TRASLACIÓN SE DA PARTIENDO DESDE LAS COORDENADAS DEL CENTRO DE LA FIGURA HACIA LAS COORDENADAS QUE SE INDIQUEN.
        this.translate(
            left = -200f,//INDICANDO VALORES POSITIVO MOVEMOS LA FIGURA A LA DERECHA Y CON NEGATIVOS A LA IZQUIERDA
            top = 0f//INDICANDO VALORES POSITIVO MOVEMOS LA FIGURA HACIA ARRIBA Y CON NEGATIVOS HACIA ABAJO
        ){
            this.drawCircle(
                color = Color.Blue,
                radius = 100.dp.toPx(),
                center = center//SI NO ESPECIFICAMOS EL CENTRO DEL CIRCULO SE TOMA POR DEFECTO ESTE MISMO VALOR
            )
        }
    }
}

@Composable
fun MiEjemploDeTransformacionDeRotacion(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ.
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        //ESTA TRANSFORMACIÓN PERMITE ROTAR UNA FIGURA EN EL SENTIDO DE LAS AGUJAS DEL RELO
        //EL PIVOTE ES LA COORDENADA QUE SERÁ EL CENTRO DE LA ROTACIÓN
        this.rotate(
            degrees = 10f,//ANGULO QUE QUEREMOS QUE ROTE EL DIBUJO
            pivot = this.center//COORDENADA SOBRE LA QUE VA A ROTAR LA FIGURA
        ){

            //DIBUJAMOS UN RECTANGULO
            this.drawRect(
                color = Color.Gray,
                size = this.size/3f,//TAMAÑO DEL RECTANGULO
                topLeft = Offset(x= (this.size.width/3f), y= (this.size.height/3f))//ACÁ UBICAMOS LA PARTE SUPERIOR IZQUIERDA CON BASE EN UN CALCULO DADO.
            )
        }
    }
}

@Composable
fun MiEjemploDeTransformacionDeInserccionDeRelleno(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        //TAMÁÑO PARA LA FIGURA CALCULADO A PARTIR DEL TAMAÑO DEL LIENZO DIVIDIDO EN 2
        val tamanioDelLienzo: Size = (this.size/3f)

        //ESTA TRANSFORMACIÓN AÑADE UN PADDING O RELLENO EXTERIOR EN HORIZONTAL Y VERTICAL, LO QUE HACE QUE LA FIGURA SE DESPLACE EN
        //LOS CUATRO SENTIDOS [DERECHA, ARRIBA, IZQUIERDA, ABAJO]
        this.inset(
            horizontal = 0f,//INDICANDO VALORES POSITIVOS MOVEMOS LA FIGURA A LA DERECHA Y CON NEGATIVOS A LA IZQUIERDA
            vertical = 0f,//INDICANDO VALORES POSITIVOS MOVEMOS LA FIGURA HACIA ARRIBA Y CON NEGATIVOS HACIA ABAJO
        ){
            this.drawRect(//DIBUJAMOS UN RECTANGULO
                color = Color.Green,
                size = tamanioDelLienzo,//EL TAMAÑO LO DEFINIMOS COMO LA TERCERA PARTE
                topLeft = Offset(x= 400F, y=400F)//this.center//ACÁ SE UBICA LA PARTE SUPERIOR IZQUIERDA DE LA FIGURA EN LAS COORDENADAS DEL CENTRO DEL LIENZO, SI CONFIGURAMOS EL CENTRO CON EL CENTRO DEL LIENZO SE PIERDE EL FUNCIONAMIENTO DE LA TRANSFORMACIÓN
            )
        }
    }
}

@Composable
fun MiEjemploDeAplicarTransformacionesMultiples(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() }
){
    //CONTENEDOR O LIENZO EN EL QUE SE DIBUJARÁ
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        //ESTA TRANSFORMACIÓN PERMITE ADICIONAR VARIAS TRANSFORMACIONES PARA UNA FIGURA
        this.withTransform(
            transformBlock = {
                this.translate(
                    left = (size.width/5F)
                )
                this.rotate(
                    degrees = 0F
                )
            }
        ) {
            this.drawRect(
                color = Color.Gray,
                topLeft = Offset(x = (this.size.width/4F), y = (this.size.height/3F) ),//COORDENADAS INICIAL DE LA ESQUINA SUPERIOR IZQUIERDA DE LA FIGURA ANTES DE LA TRANSFORMACIÓN
                size = (size/3F)//TAMAÑO DE LA FIGURA
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MisEjemplosDeTransformacionesPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploDeAplicarTransformacionesMultiples()
        }
    }
}