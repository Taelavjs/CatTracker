package com.example.cattracker.graphing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cattracker.database.DatabaseProvider
import java.time.Instant
import java.time.ZoneId

@Composable
fun InsulinScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val database = remember {
        DatabaseProvider.getDatabase(context)
    }

    val dao = database.insulinReadingsDao()

    val viewModel: InsulinClassViewModel = viewModel {
        InsulinClassViewModel(dao)
    }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePicked = viewModel.selectedDate.collectAsState().value
    val textFieldState = rememberTextFieldState("")
    var showAddInsulinDialog by remember { mutableStateOf(false) }

    val readings by viewModel.selectedDateReadings
        .collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            InsulinReadingsView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                insulinReadings = readings,
                datePicked = datePicked
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                DatePickerButtons(
                    datePicked = datePicked,
                    setDate = viewModel::setDate,
                    onShowDatePickerChange = { showDatePicker = it }
                )
            }
        }

        FloatingActionButton(
            onClick = { showAddInsulinDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add insulin"
            )
        }

        if (showAddInsulinDialog) {
            DialogInsulinInput(
                addInsulinReading = { reading ->
                    viewModel.addInsulinReading(reading)
                },
                textFieldState = textFieldState,
                hideInsulinDialog = {
                    showAddInsulinDialog = false
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
}
