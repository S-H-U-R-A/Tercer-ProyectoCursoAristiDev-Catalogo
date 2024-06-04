package com.example.jetpackcomposecatalogoelementosui.ui.paginador

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun MyPagerVerticalExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
){
    val pagerState: PagerState = rememberPagerState { 30 }

    val scope: CoroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.DarkGray)
        ) {
            VerticalPager(
                state = pagerState,
                modifier = modifier.weight(1f)
            ) { indexPage: Int ->//NÚMERO DE CADA PÁGINA
                Text(
                    text = "Page: $indexPage",
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp)
            ) {
                repeat(times = pagerState.pageCount) { indice:Int ->//DIBUJAMOS CAJAS REDONDAS POR CADA PAGINA QUE HAY EN EL PAGINADOR
                    //SI LA PÁGINA ACTUAL COINCIDE CON EL INDICE ENTONCE LO RESALTAMOS CON UN COLOR
                    val color = if (pagerState.currentPage == indice) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(all = 2.dp)
                            .clip(shape = CircleShape)
                            .background(color = color)
                            .size(size = 16.dp)
                    )
                }
            }
        }
        Button(onClick = {
            scope.launch {
                //pagerState.scrollToPage(page = 5) //A través de estado podemos cambiar la página sin animación
                pagerState.animateScrollToPage(//A través de estado podemos cambiar la página con animación
                    page = 5,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            }
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Saltar a la página 5")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyPagerVerticalExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface {
            MyPagerVerticalExampleBasic()
        }
    }
}