package com.example.cattracker.database

import androidx.room3.ColumnTypeConverters
import com.example.cattracker.database.insulinReadings.InsulinReadings
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.cattracker.database.catsRegistered.CatsRegisteredDao
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao
import com.example.cattracker.database.catsRegistered.CatsRegistered
@Database(
    entities = [
        InsulinReadings::class,
        CatsRegistered::class
    ],
    version = 2
)
@ColumnTypeConverters(DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun insulinReadingsDao(): InsulinReadingsDao
    abstract fun catsRegisteredDao() : CatsRegisteredDao
}
