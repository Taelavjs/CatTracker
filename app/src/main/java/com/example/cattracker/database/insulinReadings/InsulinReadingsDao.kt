package com.example.cattracker.database.insulinreadings

import androidx.room3.ColumnTypeConverters
import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.cattracker.database.DateTimeConverters
import com.example.cattracker.database.insulinReadings.InsulinReadings
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
@ColumnTypeConverters(DateTimeConverters::class)
interface InsulinReadingsDao {

    @Insert
    suspend fun insert(reading: InsulinReadings)

    @Query(
        "SELECT * FROM insulin_readings " +
                "WHERE dateRecorded = :date AND catId = :catId " +
                "ORDER BY timeRecorded"
    )
    fun getReadingsForDate(date: LocalDate, catId: Int): Flow<List<InsulinReadings>>

    @Delete
    suspend fun delete(reading: InsulinReadings)

    @Update
    suspend fun update(reading: InsulinReadings)
}
