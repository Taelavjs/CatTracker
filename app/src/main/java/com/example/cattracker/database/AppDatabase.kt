package com.example.cattracker.database

import androidx.room3.ColumnTypeConverters
import com.example.cattracker.database.insulinReadings.InsulinReadings
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao

@Database(
    entities = [InsulinReadings::class],
    version = 1
)
@ColumnTypeConverters(DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun insulinReadingsDao(): InsulinReadingsDao
}
