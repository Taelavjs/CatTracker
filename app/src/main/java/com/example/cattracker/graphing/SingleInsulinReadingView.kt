package com.example.cattracker.graphing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun InsulinReading(
    modifier: Modifier = Modifier,
    deleteReading: (id : Int) -> Unit,
    data : InsulinData
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Insulin : ${data.insulinReading}", Modifier.weight(1f))
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
                deleteReading(data.id)
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun InsulinReadingPreview() {
    InsulinReading(
        data = InsulinData(
            insulinReading = 5.5f,
            time = LocalTime.of(14, 30),
            date = LocalDate.now(),
            id = 5
        ),
        modifier = Modifier,
        deleteReading = {  }
    )
}