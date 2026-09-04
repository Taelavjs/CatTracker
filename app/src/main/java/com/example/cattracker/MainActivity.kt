package com.example.cattracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cattracker.database.DatabaseProvider

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = DatabaseProvider.getDatabase(applicationContext)
        val insulinPageDao = database.insulinReadingsDao()
        val catRegisterDao = database.catsRegisteredDao()
        val catRegisteredDao = database.catsRegisteredDao()
        val catFoodDao = database.catFoodDao()

        setContent {
            CatApp(
                insulinPageDao = insulinPageDao,
                catRegisterDao = catRegisterDao,
                catRegisteredDao = catRegisterDao,
                catFoodDao = catFoodDao
            )
        }
    }
}
