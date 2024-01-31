package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyScrollTabRowExampleBasic(
    modifier: Modifier = Modifier
){
    var stateTab: Int by remember { mutableIntStateOf(0) }

    val titles: List<String> = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6")
    val icons: List<ImageVector> = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings, Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = stateTab,
            edgePadding = 0.dp
        ) {
            titles.forEachIndexed { index, itemLabel ->
                Tab(
                    selected = (stateTab == index) ,
                    onClick = { stateTab = index },
                    text = {
                        Text(
                            text = itemLabel,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = itemLabel
                        )
                    }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${stateTab + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun MyScrollTabRowExampleBasicPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyScrollTabRowExampleBasic()
        }
    }
}