package com.example.cattracker

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cattracker.graphing.InsulinClassViewModel
import com.example.cattracker.graphing.InsulinScreen
import com.example.cattracker.main.appTopBottomBars.CatAppBottomBar
import com.example.cattracker.main.appTopBottomBars.CatAppTopBar
import com.example.cattracker.main.catSelectionSheet.CatSelectionSheet
import com.example.cattracker.registerCatView.RegisterCatViewModel
import com.example.cattracker.registerCatView.RegisterNewCat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatTrackerScreen(
    insulinViewModel: InsulinClassViewModel,
    catsViewModel: RegisterCatViewModel
) {
    var showInsulinTextReadings by rememberSaveable {
        mutableStateOf(true)
    }

    var showAddInsulinDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var showRegisterPage by rememberSaveable {
        mutableStateOf(false)
    }

    val allCats by catsViewModel
        .cats
        .collectAsState(initial = emptyList())

    var activeCatId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    val activeCat = allCats.firstOrNull {
        it.id == activeCatId
    } ?: allCats.firstOrNull()

    Scaffold(
        topBar = {
            CatAppTopBar(
                catName = activeCat?.name,
                onMenuClick = {
                    showBottomSheet = true
                }
            )
        },
        bottomBar = {
            CatAppBottomBar(
                showInsulinTextReadings = showInsulinTextReadings,
                onToggleTextReadings = {
                    showInsulinTextReadings =
                        !showInsulinTextReadings
                },
                onAddInsulinReadingClick = {
                    showAddInsulinDialog = true
                }
            )
        }
    ) { innerPadding ->

        if (activeCat == null || showRegisterPage) {
            RegisterNewCat(
                modifier = Modifier.padding(innerPadding),
                viewModel = catsViewModel,
                onRegister = {
                    showRegisterPage = false
                }
            )
        } else {
            InsulinScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = insulinViewModel,
                showInsulinTextReadings = showInsulinTextReadings,
                showAddInsulinDialog = showAddInsulinDialog,
                onShowAddInsulinDialogChange = {
                    showAddInsulinDialog = it
                },
                activeCat = activeCat
            )
        }
    }

    if (showBottomSheet) {
        CatSelectionSheet(
            cats = allCats,
            onCatSelected = { cat ->
                activeCatId = cat.id
                showBottomSheet = false
            },
            onRegisterNewCat = {
                showRegisterPage = true
                showBottomSheet = false
            },
            onDismiss = {
                showBottomSheet = false
            }
        )
    }
}
