package com.example.cattracker

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cattracker.database.catsRegistered.CatsRegisteredDao
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao
import com.example.cattracker.graphing.InsulinClassViewModel
import com.example.cattracker.registerCatView.RegisterCatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatApp(
    insulinPageDao: InsulinReadingsDao,
    catRegisterDao: CatsRegisteredDao
) {
    val insulinViewModel: InsulinClassViewModel = viewModel {
        InsulinClassViewModel(insulinPageDao)
    }

    val catsViewModel: RegisterCatViewModel = viewModel {
        RegisterCatViewModel(catRegisterDao)
    }

    CatTrackerScreen(
        insulinViewModel = insulinViewModel,
        catsViewModel = catsViewModel
    )
}
