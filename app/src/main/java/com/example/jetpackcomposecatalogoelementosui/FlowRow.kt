package com.example.jetpackcomposecatalogoelementosui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyFlowRowExample(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = 3,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
    ) {
        repeat(10){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(50.dp)
                    .height(50.dp)
                    .background(Color.Cyan)
            ) {
                Text(
                    text = it.toString(),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyFlowRowExampleAdvance(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = 3,
        modifier = modifier
            .fillMaxWidth(fraction = 1f)
            .wrapContentHeight(align = Alignment.Top)
    ) {
        repeat(10){index: Int ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(50.dp)
                    .height(50.dp)
                    .background(Color.Cyan)
                    .weight(weight = if (index % 2 == 0) 1f else 2f, fill = true)
            ) {
                Text(
                    text = index.toString(),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@Preview(
    name = "Example Flow Row",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyFlowRowPreview(){
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyFlowRowExampleAdvance()
        }
    }
}