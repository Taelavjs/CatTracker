package com.example.cattracker.graphing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cattracker.database.catsRegistered.CatsRegistered
import com.example.cattracker.database.insulinReadings.InsulinReadings
import java.time.format.DateTimeFormatter

@Composable
fun InsulinReading(
    modifier: Modifier = Modifier,
    deleteReading: (reading: InsulinReadings) -> Unit,
    updateInsulinReading: (InsulinReadings) -> Unit,
    data: InsulinData,
    activeCat: CatsRegistered
) {
    var showEditDialog by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Insulin : ${data.insulinReading}",
            modifier = Modifier
                .weight(1f)
                .clickable {
                    showEditDialog = true
                }
        )

        Text(
            text = data.time.format(
                DateTimeFormatter.ofPattern("HH:mm")
            ),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "x",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                deleteReading(
                    InsulinReadings(
                    id = data.id,
                    insulinReading = data.insulinReading,
                    timeRecorded = data.time,
                    dateRecorded = data.date,
                        catId = activeCat.id
                ))
            }
        )
    }

    if (showEditDialog) {
        EditInsulinDialog(
            currentValue = data.insulinReading,
            onDismiss = {
                showEditDialog = false
            },
            onSave = { newValue ->
                updateInsulinReading(
                    InsulinReadings(
                        id = data.id,
                        insulinReading = newValue,
                        timeRecorded = data.time,
                        dateRecorded = data.date,
                        catId = activeCat.id
                    )
                )
                showEditDialog = false
            }
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun InsulinReadingPreview() {
//    InsulinReading(
//        deleteReading = { },
//        updateInsulinReading = { },
//        data = InsulinData(
//            insulinReading = 5.5f,
//            time = LocalTime.of(14, 30),
//            date = LocalDate.now(),
//            id = 5
//        ),
//    )
//}

@Composable
fun EditInsulinDialog(
    currentValue: Float,
    onDismiss: () -> Unit,
    onSave: (Float) -> Unit
) {
    val textFieldState = rememberTextFieldState(
        initialText = currentValue.toString()
    )

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Edit insulin")

                TextField(
                    state = textFieldState,
                    label = {
                        Text("Insulin")
                    }
                )

                Row {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            val value =
                                textFieldState.text.toString().toFloatOrNull()

                            if (value != null) {
                                onSave(value)
                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}