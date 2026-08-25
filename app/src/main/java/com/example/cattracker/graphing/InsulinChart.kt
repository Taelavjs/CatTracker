package com.example.cattracker.graphing

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun InsulinReadingsView(
    modifier: Modifier = Modifier,
    insulinReadings: List<InsulinData>,
    datePicked: LocalDate?
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = datePicked?.format(
                        DateTimeFormatter.ofPattern("dd MMM yyyy")
                    ) ?: "No date selected",
                    textDecoration = TextDecoration.Underline
                )
            }

            HorizontalDivider(thickness = 2.dp)
        }
        items(insulinReadings) { reading ->
            InsulinReading(data = reading)
        }
    }
}
