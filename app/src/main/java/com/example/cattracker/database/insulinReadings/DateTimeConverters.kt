package com.example.cattracker.database

import androidx.room3.ColumnTypeConverter
import java.time.LocalDate
import java.time.LocalTime

class DateTimeConverters {

    @ColumnTypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.toString()
    }

    @ColumnTypeConverter
    fun toLocalDate(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @ColumnTypeConverter
    fun fromLocalTime(time: LocalTime): String {
        return time.toString()
    }

    @ColumnTypeConverter
    fun toLocalTime(time: String): LocalTime {
        return LocalTime.parse(time)
    }
}
