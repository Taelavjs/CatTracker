package com.example.cattracker.graphing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime

data class InsulinData(
    val insulinReading : Float,
    val date : LocalDate,
    val time : LocalTime
)

class InsulinClassViewModel : ViewModel() {
    private val _insulinData = MutableStateFlow(
        listOf(
            InsulinData(
                insulinReading = 4f,
                date = LocalDate.now(),
                time = LocalTime.of(7, 30)
            ),
            InsulinData(
                insulinReading = 6f,
                date = LocalDate.now(),
                time = LocalTime.of(9, 15)
            ),
            InsulinData(
                insulinReading = 3f,
                date = LocalDate.now(),
                time = LocalTime.of(12, 0)
            ),
            InsulinData(
                insulinReading = 5f,
                date = LocalDate.now(),
                time = LocalTime.of(14, 30)
            ),
            InsulinData(
                insulinReading = 2f,
                date = LocalDate.now(),
                time = LocalTime.of(18, 45)
            ),
            InsulinData(
                insulinReading = 4f,
                date = LocalDate.now(),
                time = LocalTime.of(21, 15)
            )
        )
    )
    val insulinData: StateFlow<List<InsulinData>> =
        _insulinData.asStateFlow()
    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    var selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    fun addInsulinReading(reading: Float) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundedReading = df.format(reading).toFloat()
        _insulinData.value += InsulinData(
            insulinReading = roundedReading,
            date = LocalDate.now(),
            time = LocalTime.now()
        )
    }

    fun setDate(date: LocalDate){
        _selectedDate.value = date
    }

    val selectedDateReadings: StateFlow<List<InsulinData>> =
        combine(
            insulinData,
            selectedDate
        ) { readings, date ->
            readings.filter { it.date == date }.sortedBy { it.time  }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )
}
