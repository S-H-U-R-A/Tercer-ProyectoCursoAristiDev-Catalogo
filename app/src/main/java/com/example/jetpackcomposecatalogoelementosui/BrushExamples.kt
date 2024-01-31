package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyBrushBasicExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    /*    val brush = Brush.verticalGradient(
            listOf(Color.Red, Color.Blue),
        )

        Column(
            modifier = modifier.background(
                color = Color.White
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Canvas(
                modifier = Modifier.size(200.dp),
            ){
                drawCircle(brush = brush)
            }
        }*/

    /*    val intervalosDeColor: Array< Pair<Float, Color> > = arrayOf(
        0.0f to Color.Yellow,
        Pair(0.5f, Color.Red),
        1f to Color.Blue
    )

    Box(
        modifier = modifier
            .requiredSize(size = 200.dp)
            .background(
                brush= Brush.horizontalGradient(
                    colorStops = intervalosDeColor
                )
            )
    )*/

    val listaDeColores : List<Color> = listOf(Color.Red, Color.Yellow, Color.Blue)

    val finGradienteEnX: Float = with(LocalDensity.current){
        100.dp.toPx()
    }

    val inicioGradienteEnX: Float = with(LocalDensity.current){
        50.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listaDeColores,//DEGRADADO DE COLORES A APLICAR
                    startX = inicioGradienteEnX,
                    endX = finGradienteEnX,
                    tileMode = TileMode.Clamp
                )
            )
            .border(width = 1.dp, Color.Red)
    )


}


@Composable
fun MyBrushImageExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val pincelImagen = ShaderBrush(
        shader = ImageShader(
            image = ImageBitmap.imageResource(id = R.drawable.thor)
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space= 16.dp)
    ) {
        Text(
            text = "Hello Android!",
            style = TextStyle(
                brush = pincelImagen,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
        )

        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .clip(shape = CutCornerShape(size = 16.dp))
                .background(brush = pincelImagen)

        )

        Canvas(
            modifier = Modifier.size(200.dp)
        ){
            drawCircle(brush= pincelImagen)
        }


    }
}

@Preview
@Composable
fun MyBrushExamplesPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyBrushImageExample()
        }
    }
}