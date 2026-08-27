package com.example.cattracker.database.catsRegistered

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "cats_registered")
data class CatsRegistered(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String
)
