package com.example.cattracker.graphing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.example.cattracker.database.catsRegistered.CatsRegistered
import com.example.cattracker.graphing.line_graph.TransformInsulinReadingsIntoPoints
import java.time.Instant
import java.time.ZoneId

@Composable
fun InsulinScreen(
    modifier: Modifier = Modifier,
    viewModel: InsulinClassViewModel,
    showInsulinTextReadings: Boolean,
    showAddInsulinDialog: Boolean,
    onShowAddInsulinDialogChange: (Boolean) -> Unit,
    activeCat: CatsRegistered
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePicked by  viewModel.selectedDate.collectAsStateWithLifecycle()
    val textFieldState =  rememberTextFieldState("")
    val readings by viewModel
        .getReadingsForCat(activeCat.id)
        .collectAsState(initial = emptyList())


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            readings.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No readings for today")
                }
            }

            showInsulinTextReadings -> {
                InsulinReadingsView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    insulinReadings = readings,
                    datePicked = datePicked,
                    deleteById = viewModel::deleteInsulinReading,
                    editReading = viewModel::updateInsulinReading,
                    activeCat = activeCat
                )
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    TransformInsulinReadingsIntoPoints(
                        insulinReadings = readings
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DatePickerButtons(
                datePicked = datePicked,
                setDate = viewModel::setDate,
                onShowDatePickerChange = {
                    showDatePicker = it
                }
            )
        }
    }

    if (showAddInsulinDialog) {
        DialogInsulinInput(
            addInsulinReading = { reading ->
                viewModel.addInsulinReading(reading, activeCat.id)
            },
            textFieldState = textFieldState,
            hideInsulinDialog = {
                onShowAddInsulinDialogChange(false)
            }
        )
    }

    if (showDatePicker) {
        DatePickerModalInput(
            selectedDate = datePicked,
            onDateSelected = { millis ->
                if (millis != null) {
                    val date = Instant.ofEpochMilli(millis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()

                    viewModel.setDate(date)
                    showDatePicker = false
                }
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }
}
