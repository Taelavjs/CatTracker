package com.example.cattracker.registerCatView

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun RegisterNewCat(
    viewModel: RegisterCatViewModel,
    modifier: Modifier = Modifier,
    onRegister: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = { name = it }
        )

        Button(
            onClick = {
                viewModel.registerNewCat(name)
                name = ""
                onRegister()
            }
        ) {
            Text("Submit")
        }
    }
}