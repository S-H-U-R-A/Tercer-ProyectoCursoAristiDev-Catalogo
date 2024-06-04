package com.example.jetpackcomposecatalogoelementosui.ui.graficos.pincel_brush

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.R
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MiEjemploBasicoDePincelConRellenoDeImagen(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    //CREAMOS UN PINCEL PERSONALIZADO USANDO EL MÉTODO ShaderBrush() QUE UTILIZA UN SHARDER PARA PINTAR EN ESTE CASO UNA IMAGEN
    //SHADER ES UN OBJETO NATIVO DE ANDROID JAVA QUE PERMITE CREAR GRAFICOS COMPLEJOS
    //EN EL CONTEXTO DE UN PINCEL EL SHADER DEFINE COMO SE DEBE PINTAR UN ÁREA CON UN PATRON EN ESTE CASO Repeated
    //Y SI SE DEBE PINTAR USANDO UNA IMAGEN O ALGUN TIPO DE GRADIENTE
    val pincelImagen: ShaderBrush = ShaderBrush(
        shader = ImageShader(
            image = ImageBitmap.imageResource(id = R.drawable.thor),//IMAGEN USADA COMO PINTURA PARA EL PINCEL
            tileModeX = TileMode.Repeated,//SI LA IMAGEN YO LLENA EL LIENZO, ENTONCES DEBEMOS INDICAR COMO RELLENAR LOS ESPACIOS VACIOS EN EL EJE X
            tileModeY = TileMode.Repeated//SI LA IMAGEN YO LLENA EL LIENZO, ENTONCES DEBEMOS INDICAR COMO RELLENAR LOS ESPACIOS VACIOS EN EL EJE Y
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = "Hello Android!",
            style = TextStyle(
                brush = pincelImagen,//PODEMOS USAR EL PINCEL PARA EL COLOR DE RELLENO DE UN TEXTO
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
        )

        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .clip(shape = CutCornerShape(size = 16.dp))
                .background(brush = pincelImagen)//PODEMOS USAR EL PINCEL PARA EL COLOR DE FONDO DE UN COMPOSABLE
        )

        Canvas(
            modifier = Modifier.size(200.dp)
        ){
            this.drawCircle(brush= pincelImagen)//PODEMOS USAR EL PINCEL PARA EL COLOR DE RELLENO DE UNA FIGURA
        }
    }
}


@Composable
fun MiEjemploBasicoDePincelConRellenoDeGradienteLinearVertical(
    modifier: Modifier = Modifier,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    //CREAMOS UN PINCEL PERSONALIZADO USANDO EL MÉTODO ShaderBrush() QUE UTILIZA UN SHARDER PARA PINTAR, EN ESTE CASO UN
    //GRADIENTE VERTICAL
    val pincelLinear: ShaderBrush = ShaderBrush(
        shader = LinearGradientShader(//SE USA PARA PINTAR GRADIENTES DIAGONALES, VERTICALES U HORIZONTALES
            colors = listOf(Color.DarkGray, Color.Red),//LISTADO DE COLORES A SER USADOS EN EL GRADIENTE
            from = Offset(x = 0F, y = 0F),//POSICIÓN INICIAL PARA PINTAR EL GRADIENTE, ASUMIENDO QUE ESTE PINTA COMO UNA BROCHA
            to = Offset(x = 0F, y = 200f),//EN ESTE CASO INDICAMOS QUE EL GRADIENTE SE PINTE SOLO DE FORMA VERTICAL OSEA SOBRE EL EJE Y
            tileMode = TileMode.Mirror//LOS ESPACIOS POR RELLENAR LO VA A HACER CON EL MODO ESPEJO EN EL QUE EL ULTIMO COLOR QUE SE USE SERÁ EL PRIMERO A SER USADO LA PROXIMA VEZ
        )
    )

    Box(
        modifier = modifier
            .requiredSize(300.dp)//SE DA UNA ALTURA Y ANCHURA FIJA Y PRIORITARIA
            .background(
                brush = pincelLinear,//PINTAMOS EL FONDO DE LA CAJA A PARTIR DE USAR UN PINCEL PERSONALIZADO CON UN GRADIENTE VERTICAL
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(all = 16.dp)

    ) {
        Text(
            color = Color.White,
            text = "El fondo de Gradiente vertical de la caja fue pintado con un PINCEL/BRUSH PERSONALIZADO CON SHADER"
        )
    }
}


@Preview
@Composable
fun MisEjemplosDePincelesPersonalizadosConRellenoDeImagenPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MiEjemploBasicoDePincelConRellenoDeGradienteLinearVertical()
        }
    }
}