package com.example.cattracker.database.catsRegistered

import androidx.room3.ColumnTypeConverters
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.cattracker.database.DateTimeConverters
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "cats_registered")
data class CatsRegistered(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String
)
