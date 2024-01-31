package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyBoxWithConstraintsExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    BoxWithConstraints(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.tertiary)
            .border(width = 2.dp, color = Color.Magenta)
    ) {

        val ancho = this.maxWidth.value
        val alto = this.maxHeight.value

        val minAncho = this.minWidth
        val minAlto = this.minHeight

        val isFixedHeigth = this.constraints.hasFixedHeight

        Text(text = "Es altura fija: $isFixedHeigth", modifier = Modifier.align(alignment = Alignment.TopCenter))

        Text(text = "ancho: $ancho X alto: $alto", modifier = Modifier.align(alignment = Alignment.Center))

        Text(text = "minAncho: $minAncho X minAlto: $minAlto ", modifier = Modifier.align(alignment = Alignment.BottomStart))

    }
}

@Composable
fun MyBoxWithConstraintsExampleTwo(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val rectangleHeight: Dp = 100.dp

        if (this.maxHeight < rectangleHeight * 2) {
            Box(
                Modifier
                    .size(50.dp, rectangleHeight)
                    .background(Color.Blue))
        } else {
            Column {
                Box(modifier = Modifier
                    .size(width = 50.dp, rectangleHeight)
                    .background(Color.Blue))
                Box(modifier = Modifier
                    .size(50.dp, rectangleHeight)
                    .background(Color.Gray))
            }
        }

    }
}



@Preview(
    name = "Box with constraints example",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyBoxWithConstraintsPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyBoxWithConstraintsExample(
                modifier = Modifier.background(color = Color.Magenta)
            )
        }
    }
}