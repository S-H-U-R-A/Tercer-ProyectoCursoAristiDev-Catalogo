package com.example.jetpackcomposecatalogoelementosui.ui.listas

import android.content.res.Configuration
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme


@Composable
fun MySimpleLazyColumnViewExample(
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource = remember { MutableInteractionSource() }
) {
    val myList: List<String> = listOf("Aris", "Pepe", "Manolo", "Jaime")

    LazyColumn {

        item {
            Text(text = "Header")
            HorizontalDivider()
        }

        items(7) {
            Text(text = "Este es el item $it")
        }

        this.items(myList) {
            Text(text = "Hola me llamo $it")
        }

        item {
            HorizontalDivider()
            Text(text = "Footer")
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MySimpleRecyclerViewExamplePreview() {
    JetPackComposeCatalogoElementosUiTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MySimpleLazyColumnViewExample()
        }
    }
}