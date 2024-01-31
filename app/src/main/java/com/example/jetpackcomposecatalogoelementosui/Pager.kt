package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPagerExampleBasic(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val pagerState: PagerState = rememberPagerState( pageCount = { 10 } )

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(0.dp),
            pageSize = PageSize.Fixed(400.dp), //Como se distribuyen las páginas en el paginador, si queremos multiples pantallas al mismo momento especificando cuantos dp de pantalla mide cada una
            beyondBoundsPageCount = 1, //Número de páginas precargadas a la izq y der de la página que tiene visible la pantalla, si se ponen muchas se pierde la carga dinamica y mejora de performance
            pageSpacing = 16.dp, // Espaciado horizontal en este caso entre páginas.
            verticalAlignment = Alignment.CenterVertically, //Alineación vertical del contenido
            //flingBehavior = SnapFlingBehavior() //Animaciones para el deslizamiento
            userScrollEnabled = true, //Permite el desplazamiento por parte del usuario
            reverseLayout = false, //Cambia las páginas de derecha a izquierda
            key = {
                "id-$it"
            },
            //pageNestedScrollConnection = NestedScrollConnection() //Indica como se comportará el Scroll de pager cuando esta en composables que tienen Scroll como una lista Lazy
            modifier = Modifier.weight(weight = 1f)
        ) { indexPage: Int ->
            Text(
                text = "Page: $indexPage",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { indice:Int ->
                val color = if (pagerState.currentPage == indice) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }

        Button(onClick = {
            coroutineScope.launch {
                //pagerState.scrollToPage(page = 5) //A través de estado podemos cambiar la página sin animación
                pagerState.animateScrollToPage(
                    page = 5,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) //A través de estado podemos cambiar la página con animación
            }
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Jump to Page 5")
        }
    }




}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPagerExampleBasic2(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val pagerState: PagerState = rememberPagerState( pageCount = { 10 } )

    val coroutineScope = rememberCoroutineScope()

    val fling: SnapFlingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(pages = 5)//Acá indicamos cuantas páginas se pueden pasar por gesto de arrastre
    )


    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(all = 0.dp),
            pageSize = PageSize.Fixed(400.dp), //Como se distribuyen las páginas en el paginador, si queremos multiples pantallas al mismo momento especificando cuantos dp de pantalla mide cada una
            beyondBoundsPageCount = 1, //Número de páginas precargadas a la izq y der de la página que tiene visible la pantalla, si se ponen muchas se pierde la carga dinamica y mejora de performance
            pageSpacing = 16.dp, // Espaciado horizontal en este caso entre páginas.
            verticalAlignment = Alignment.CenterVertically, //Alineación vertical del contenido
            flingBehavior = fling, //Indica la cantidad máxima de páginas a pasar cuando se hace el gesto de arrastre
            userScrollEnabled = true, //Permite el desplazamiento por parte del usuario
            reverseLayout = false, //Cambia las páginas de derecha a izquierda
            key = {// Un identificador personalizado para cada página
                "id-$it"
            },
            //pageNestedScrollConnection = NestedScrollConnection() //Indica como se comportará el Scroll de pager cuando esta en composables que tienen Scroll como una lista Lazy
            modifier = Modifier.weight(weight = 1f)
        ) { indexPage: Int ->
            Text(
                text = "Page: $indexPage",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { indice:Int ->
                val color = if (pagerState.currentPage == indice) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    //pagerState.scrollToPage(page = 5) //A través de estado podemos cambiar la página sin animación
                    pagerState.animateScrollToPage(
                        page = 5,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    ) //A través de estado podemos cambiar la página con animación
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Jump to Page 5")
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyPagerPreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyPagerExampleBasic2()
        }
    }
}