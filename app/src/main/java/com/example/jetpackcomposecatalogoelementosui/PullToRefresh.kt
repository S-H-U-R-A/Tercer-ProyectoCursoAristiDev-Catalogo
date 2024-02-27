package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPullToRefreshExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
) {
    var itemCount by remember { mutableIntStateOf(value= 25) }

    val state = rememberPullToRefreshState( positionalThreshold = 50.dp )

    if(state.isRefreshing){
        LaunchedEffect(key1= true) {
            // fetch something
            delay(timeMillis = 1500)
            itemCount += 5
            state.endRefresh()
        }
    }

    Box(
        modifier= Modifier
            .nestedScroll(connection= state.nestedScrollConnection)
    ) {
        PullToRefreshContainer(
            state = state,
            contentColor = Color.Magenta, // Color del indicador
            containerColor = Color.Gray, //Color del contenedor
            indicator = { state: PullToRefreshState ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    LinearProgressIndicator()
                }
            },
            modifier = Modifier
                .align(alignment= Alignment.TopCenter)
                .border(width = 1.dp, color= Color.Red),
        )

        LazyColumn(
            modifier= Modifier.fillMaxSize().border(width = 1.dp, color= Color.Red),
        ) {
            if (!state.isRefreshing) {
                items(itemCount) {
                    ListItem(
                        headlineContent = {
                            Text(text = "Item ${itemCount - it}")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPullToRefreshExample2(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember {MutableInteractionSource()}
) {
    var itemCount by remember { mutableIntStateOf(value= 25) }

    val state: PullToRefreshState = remember {
        PullToRefreshState(
            positionalThresholdPx = 200f,
            initialRefreshing = false
        )
    }

    if(state.isRefreshing){
        LaunchedEffect(key1= true) {
            // fetch something
            delay(timeMillis = 1500)
            itemCount += 5
            state.endRefresh()
        }
    }

    Box(
        modifier= Modifier
            .nestedScroll(connection= state.nestedScrollConnection)
    ) {
        PullToRefreshContainer(
            state = state,
            contentColor = Color.Magenta, // Color del indicador
            containerColor = Color.Gray, //Color del contenedor
            indicator = { state: PullToRefreshState ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all= 8.dp)
                ){
                    LinearProgressIndicator()
                }
            },
            modifier = Modifier
                .align(alignment =  Alignment.TopCenter),
        )
        LazyColumn(
            modifier= Modifier.fillMaxSize()
        ) {
            if (!state.isRefreshing) { // Si no se esta ejecutando una actualización
                items(itemCount) {
                    ListItem(
                        headlineContent = {
                            Text(text = "Item ${itemCount - it}")
                        }
                    )
                }
            }
        }
    }
}



@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PullToRefreshExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color= MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MyPullToRefreshExample()
        }
    }
}