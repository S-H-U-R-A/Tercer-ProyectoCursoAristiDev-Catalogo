package com.example.jetpackcomposecatalogoelementosui.ui.elementos_basicos

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogoelementosui.ui.theme.JetPackComposeCatalogoElementosUiTheme

@Composable
fun MyBadgeBoxExample(
    badgeCounter: Int
) {
    BadgedBox(
        badge = {
            Badge {
                Text(
                    text = "$badgeCounter"
                )
            }
        },
        modifier = Modifier
            .padding(all = 16.dp)
            .semantics {
                contentDescription = "$badgeCounter"
            }
    ) {
        Icon(
            imageVector = Icons.Default.Air,
            contentDescription = "Estrella"
        )
    }
}



@Composable
fun MyBadgeExample() {
    Badge() {
        Text(
            text = "1"
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyPreviewBadge(){
    JetPackComposeCatalogoElementosUiTheme {
        MyBadgeBoxExample(
            badgeCounter = 12
        )
    }
}