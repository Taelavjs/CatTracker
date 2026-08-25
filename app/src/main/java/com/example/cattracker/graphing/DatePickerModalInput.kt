package com.example.cattracker.graphing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerModalInput(
    selectedDate: LocalDate,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val initialMillis = selectedDate
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialMillis,
        initialDisplayMode = DisplayMode.Input
    )
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun DatePickerButtons(
    modifier: Modifier = Modifier,
    datePicked: LocalDate,
    setDate: (LocalDate) -> Unit,
    onShowDatePickerChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                setDate(datePicked.minusDays(1))
            },
            modifier = Modifier.weight(1f)
        ) {
            Text("<")
        }

        Button(
            onClick = {
                onShowDatePickerChange(true)
            },
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = datePicked.format(
                    DateTimeFormatter.ofPattern("dd MMM yyyy")
                )
            )
        }

        Button(
            onClick = {
                setDate(datePicked.plusDays(1))
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(">")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerButtonsPreview() {
    DatePickerButtons(
        datePicked = LocalDate.of(2026, 8, 25),
        setDate = { },
        onShowDatePickerChange = { }
    )
}
