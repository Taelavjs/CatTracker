package com.example.cattracker.database.catsRegistered

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsRegisteredDao {
    @Insert
    suspend fun insert(reading: CatsRegistered)

    @Query("SELECT * FROM cats_registered")
    fun getAllCats(): Flow<List<CatsRegistered>>
}