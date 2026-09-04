package com.example.cattracker.database.catFood

import androidx.room3.Dao
import androidx.room3.Insert
@Dao
interface RecordedDietCatDao {
    @Insert
    fun recordCatFoodAte(recordedFood: RecordedDietCat)
}