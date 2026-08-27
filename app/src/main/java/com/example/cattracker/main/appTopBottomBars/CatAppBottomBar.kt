package com.example.cattracker.main.appTopBottomBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CatAppBottomBar(
    showInsulinTextReadings: Boolean,
    onToggleTextReadings: () -> Unit,
    onAddInsulinReadingClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.Transparent
    ) {
        NavigationBarItem(
            selected = true,
            onClick = onToggleTextReadings,
            icon = {
                Text(
                    if (showInsulinTextReadings) {
                        "Graph"
                    } else {
                        "Readings"
                    }
                )
            },
            label = {
                Text("Insulin")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onAddInsulinReadingClick,
            icon = {
                Text("+")
            },
            label = {
                Text("Add Reading")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatAppTopBar(
    catName: String?,
    onMenuClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = catName ?: "Cat Tracker"
            )
        },
        actions = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        }
    )
}