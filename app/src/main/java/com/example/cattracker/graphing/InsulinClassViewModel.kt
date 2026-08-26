package com.example.cattracker.graphing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cattracker.database.insulinReadings.InsulinReadings
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

data class InsulinData(
    val insulinReading : Float,
    val date : LocalDate,
    val time : LocalTime,
    val id : Int
)

class InsulinClassViewModel(
    private val dao: InsulinReadingsDao
) : ViewModel() {

    private val _selectedDate =
        MutableStateFlow(LocalDate.now())

    val selectedDate: StateFlow<LocalDate> =
        _selectedDate.asStateFlow()
    private val _readingInput = MutableStateFlow("")

    var readingInput : StateFlow<String> =
        _readingInput.asStateFlow()

    fun onReadingInputChanged(value: String) {
        _readingInput.value = value
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectedDateReadings: StateFlow<List<InsulinData>> =
        selectedDate
            .flatMapLatest { date ->
                dao.getReadingsForDate(date)
            }
            .map { readings ->
                readings.map { reading ->
                    InsulinData(
                        insulinReading = reading.insulinReading,
                        date = reading.dateRecorded,
                        time = reading.timeRecorded,
                        id = reading.id
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    fun setDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun addInsulinReading(reading: Float) {
        viewModelScope.launch {
            dao.insert(
                InsulinReadings(
                    insulinReading = reading,
                    timeRecorded = LocalTime.now(),
                    dateRecorded = LocalDate.now()
                )
            )
        }
    }

    fun deleteInsulinReading(id: Int) {
        viewModelScope.launch {
            dao.deleteById(
                id
            )
        }
    }
}
