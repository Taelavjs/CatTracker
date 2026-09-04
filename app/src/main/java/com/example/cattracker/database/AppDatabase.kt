package com.example.cattracker.database

import androidx.room3.ColumnTypeConverters
import com.example.cattracker.database.insulinReadings.InsulinReadings
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.cattracker.database.catFood.RecordedDietCat
import com.example.cattracker.database.catsRegistered.CatsRegisteredDao
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao
import com.example.cattracker.database.catsRegistered.CatsRegistered
import com.example.cattracker.database.catFood.CatFood
import com.example.cattracker.database.catFood.CatFoodDao
import com.example.cattracker.database.catFood.RecordedDietCatDao

@Database(
    entities = [
        InsulinReadings::class,
        CatsRegistered::class,
        RecordedDietCat::class,
        CatFood::class
    ],
    version = 3
)
@ColumnTypeConverters(DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun insulinReadingsDao(): InsulinReadingsDao
    abstract fun catsRegisteredDao() : CatsRegisteredDao
    abstract fun recordedDietCatDao() : RecordedDietCatDao
    abstract fun catFoodDao() : CatFoodDao
}
