package com.example.jetpackcomposecatalogoelementosui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
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
            .padding(all = 32.dp)
            .semantics {
                contentDescription = "$badgeCounter"
            }
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Estrella"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeExample() {
    Badge() {
        Text(
            text = "1"
        )
    }
}