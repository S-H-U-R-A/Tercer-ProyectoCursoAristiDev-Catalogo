//package com.example.jetpackcomposecatalogoelementosui.ui.paginador
//
//import android.content.res.Configuration
//import androidx.compose.animation.core.Spring
//import androidx.compose.animation.core.spring
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.TargetedFlingBehavior
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.PageSize
//import androidx.compose.foundation.pager.PagerDefaults
//import androidx.compose.foundation.pager.PagerSnapDistance
//import androidx.compose.foundation.pager.PagerState
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun MyPagerHorizontalExampleBasic(
//    modifier: Modifier = Modifier,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
//) {
//    val pagerState: PagerState = rememberPagerState( pageCount = { 10 } )//CANTIDAD DE PAGINAS QUE VA A TENER EL HORIZONTAL PAGER
//
//    val coroutineScope = rememberCoroutineScope()//ÁMBITO DE CORRUTINA
//
//    Column(
//        modifier = modifier
//    ) {
//        HorizontalPager(
//            state = pagerState,//ESTADO PARA RECUPERAR INFORMACIÓN SOBRE EL PAGINADOR
//            contentPadding = PaddingValues(all = 0.dp),
//            pageSize = PageSize.Fixed(200.dp),//EL ANCHO DE CADA PÁGINA, SI EL ANCHO ES POCO PODRÍAN CABER VARIAS PÁGINAS EN UNA MISMA PANTALLA
//            beyondViewportPageCount = 1,//NUMERO DE PAGINAS PRECARGADAS EN LA IZQUIERDA Y DERECHA, SI PONEMOS MUCHAS SE PIERDE EL PERFORMANCE.
//            pageSpacing = 16.dp,//ESPACIADO HORIZONTAL ENTRE LAS PÁGINAS.
//            verticalAlignment = Alignment.Top,//ALINEACIÓN DEL CONTENIDO DE LA PÁGINA
//            //flingBehavior = PagerDefaults.flingBehavior(state = pagerState),//ANIMACIÓN PARA EL DESPLAZAMIENTO, ADEMÁS SE PUEDE CONFIGURAR COSAS COMO CUANTAS PAGINAS PODEMOS PASAR AL HACER EL GESTO DEL ARRASTRE
//            userScrollEnabled = true,//PERMITIR QUE EL USUARIO DESPLACE EL PAGINADOR
//            reverseLayout = true,//CAMBIA LA HORIENTACION A DERECHA A IZQUIERDA
//            key = null,//ES UNA LLAVE QUE SE ASOCIA A CADA PAGINA, SI SE USA NULL SE LE ASOCIA LA POSICION EN LA LISTA DE PAGINAS.
//            //pageNestedScrollConnection = NestedScrollConnection(), //DEFINE COMO SE COMPORTARÁ EL SCROLL DEL PAGER CUANDO ESTAMOS EN COMPOSABLES COMO LAZYCOLUMN QUE TIENEN OTRO SCROLL
//            modifier = Modifier.weight(weight = 1f)
//        ) { indexPage: Int ->//NÚMERO DE CADA PÁGINA
//            Text(
//                text = "Page: $indexPage",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth()
//                .align(alignment = Alignment.CenterHorizontally)
//                .padding(bottom = 8.dp)
//        ) {
//            repeat(times = pagerState.pageCount) { indice:Int ->//DIBUJAMOS CAJAS REDONDAS POR CADA PAGINA QUE HAY EN EL PAGINADOR
//                //SI LA PÁGINA ACTUAL COINCIDE CON EL INDICE ENTONCE LO RESALTAMOS CON UN COLOR
//                val color = if (pagerState.currentPage == indice) Color.DarkGray else Color.LightGray
//                Box(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .clip(CircleShape)
//                        .background(color)
//                        .size(16.dp)
//                )
//            }
//        }
//        Button(onClick = {
//            coroutineScope.launch {
//                //pagerState.scrollToPage(page = 5) //A través de estado podemos cambiar la página sin animación
//                pagerState.animateScrollToPage(//A través de estado podemos cambiar la página con animación
//                    page = 5,
//                    animationSpec = spring(
//                        dampingRatio = Spring.DampingRatioHighBouncy,
//                        stiffness = Spring.StiffnessMedium
//                    )
//                )
//            }
//        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//            Text("Jump to Page 5")
//        }
//
//    }
//}
//
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun MyPagerHorizontalExampleCustom(
//    modifier: Modifier = Modifier,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
//) {
//    val pagerState: PagerState = rememberPagerState( pageCount = { 10 } )
//
//    val coroutineScope = rememberCoroutineScope()
//
//    val fling: TargetedFlingBehavior = PagerDefaults.flingBehavior(
//        state = pagerState,
//        pagerSnapDistance = PagerSnapDistance.atMost(pages = 5)//ACÁ INDICAMOS CUANTAS PÁGINAS SE PUEDEN DESPLAZAR CON EL GESTO DE ARRASTRAR
//    )
//
//    Column(modifier = modifier) {
//        HorizontalPager(
//            state = pagerState,
//            contentPadding = PaddingValues(all = 0.dp),
//            pageSize = PageSize.Fill, //Como se distribuyen las páginas en el paginador, si queremos multiples pantallas al mismo momento especificando cuantos dp de pantalla mide cada una
//            beyondViewportPageCount = 1, //Número de páginas precargadas a la izq y der de la página que tiene visible la pantalla, si se ponen muchas se pierde la carga dinamica y mejora de performance
//            pageSpacing = 16.dp, // Espaciado horizontal en este caso entre páginas.
//            verticalAlignment = Alignment.CenterVertically, //Alineación vertical del contenido
//            flingBehavior = fling, //Indica la cantidad máxima de páginas a pasar cuando se hace el gesto de arrastre
//            userScrollEnabled = true, //Permite el desplazamiento por parte del usuario
//            reverseLayout = false, //Cambia las páginas de derecha a izquierda
//            key = {// Un identificador personalizado para cada página
//                "id-$it"
//            },
//            //pageNestedScrollConnection = NestedScrollConnection() //Indica como se comportará el Scroll de pager cuando esta en composables que tienen Scroll como una lista Lazy
//            modifier = Modifier.weight(weight = 1f)
//        ) { indexPage: Int ->
//            Text(
//                text = "Page: $indexPage",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//
//        Row(
//            Modifier
//                .wrapContentHeight()
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally)
//                .padding(bottom = 8.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(pagerState.pageCount) { indice:Int ->
//                val color = if (pagerState.currentPage == indice) Color.DarkGray else Color.LightGray
//                Box(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .clip(CircleShape)
//                        .background(color)
//                        .size(16.dp)
//                )
//            }
//        }
//
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    //pagerState.scrollToPage(page = 5) //A través de estado podemos cambiar la página sin animación
//                    pagerState.animateScrollToPage(
//                        page = 5,
//                        animationSpec = spring(
//                            dampingRatio = Spring.DampingRatioHighBouncy,
//                            stiffness = Spring.StiffnessMedium
//                        )
//                    ) //A través de estado podemos cambiar la página con animación
//                }
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text("Jump to Page 5")
//        }
//    }
//}
//
//
//@Preview(
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
//)
//@Composable
//fun MyPagerHorizontalExampleBasicPreview() {
//    JetPackComposeCatalogoElementosUiTheme {
//        Surface(
//            color = MaterialTheme.colorScheme.background,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            MyPagerHorizontalExampleCustom()
//        }
//    }
//}