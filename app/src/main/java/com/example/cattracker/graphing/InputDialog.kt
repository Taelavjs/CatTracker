package com.example.cattracker.graphing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogInsulinInput(
    addInsulinReading: (Float) -> Unit,
    textFieldState: TextFieldState,
    hideInsulinDialog: () -> Unit
) {
    Dialog(
        onDismissRequest = hideInsulinDialog
    ) {
        DialogInsulinInputContent(
            addInsulinReading = addInsulinReading,
            textFieldState = textFieldState,
            hideInsulinDialog = hideInsulinDialog
        )
    }
}

@Composable
fun DialogInsulinInputContent(
    addInsulinReading: (Float) -> Unit,
    textFieldState: TextFieldState,
    hideInsulinDialog: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                state = textFieldState,
                label = { Text("Insulin") },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    val reading = textFieldState.text.toString().toFloatOrNull()

                    if (reading != null) {
                        addInsulinReading(reading)
                        textFieldState.clearText()
                        hideInsulinDialog()
                    }
                }
            ) {
                Text("Add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogInsulinInputPreview() {
    val textFieldState = TextFieldState()

    DialogInsulinInputContent(
        addInsulinReading = {},
        textFieldState = textFieldState,
        hideInsulinDialog = {}
    )
}
