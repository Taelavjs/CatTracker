package com.example.cattracker.database.insulinReadings

import com.example.cattracker.database.DateTimeConverters

import androidx.room3.ColumnTypeConverters
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "insulin_readings")
@ColumnTypeConverters(DateTimeConverters::class)
data class InsulinReadings(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var insulinReading: Float,
    var timeRecorded: LocalTime,
    var dateRecorded: LocalDate
)
