package com.example.cattracker.database.catFood

import androidx.room3.Dao
import androidx.room3.Insert

@Dao
interface CatFoodDao {
    @Insert
    fun insertNewCatFood(food: CatFood)
}