package com.example.cattracker.database.catFood

import androidx.room3.ColumnTypeConverters
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.PrimaryKey
import com.example.cattracker.database.DateTimeConverters
import com.example.cattracker.database.catsRegistered.CatsRegistered
import java.time.LocalDate
import java.time.LocalTime

@Entity(
    tableName = "recorded_diet",
    foreignKeys = [
        ForeignKey(
            entity = CatsRegistered::class,
            parentColumns = ["id"],
            childColumns = ["catId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CatFood::class,
            parentColumns = ["id"],
            childColumns = ["foodId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@ColumnTypeConverters(DateTimeConverters::class)
data class RecordedDietCat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val catId: Int,
    val foodId: Int,
    val weightGrams: Float = 0.0f,
    var timeRecorded: LocalTime,
    var dateRecorded: LocalDate
)