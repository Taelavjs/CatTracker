package com.example.cattracker.database.catFood

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "cat_food"
)
data class CatFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName: String,
    val foodType: FoodType
)

enum class FoodType {
    WETFOOD,
    DRYFOOD,
}
