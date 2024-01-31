package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeAnimationSource
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.tappableElement
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyWindowInsetExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
){

    val localDensity = LocalDensity.current

    val top = WindowInsets.safeGestures.getTop(localDensity)
    val left = WindowInsets.safeGestures.getLeft(localDensity, LayoutDirection.Ltr)
    val right = WindowInsets.safeGestures.getRight(localDensity, LayoutDirection.Ltr)
    val bottom = WindowInsets.safeGestures.getBottom(localDensity)

    val bottomNavBar = WindowInsets.navigationBars.getBottom(localDensity)

/*    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .safeGesturesPadding()
            .safeContentPadding()
    ) {
        Text(text = top.toString() , color = Color.Black)
        Text(text = "$left", color = Color.Black)
        Text(text = "$right", color = Color.Black)
        Text(text = "$bottom", color = Color.Black)
        TextField(
            value = "",
            onValueChange = {  }
        )
    }*/
    
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .imePadding() //Esto indica que el padding se ajuste cuando sale el teclado y cuando se oculte
            .imeNestedScroll() //Esto hace que cuando la lista se desplace hacia abajo se cierre el teclado
    ){
        items(20){
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                placeholder = { Text(text = "Escribe algo $it") },
                modifier = Modifier.fillMaxWidth()            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .background(color = Color.Red)
                    .windowInsetsBottomHeight(WindowInsets.systemBars) //Establecemos una altura con base en getTop del statusBar
            )
        }
    }
}

@Preview(
    name = "Example Window Inset use",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyWindowInsetPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyWindowInsetExample()
        }
    }
}