package com.example.cattracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.yml.charts.common.extensions.isNotNull
import com.example.cattracker.database.AppDatabase
import com.example.cattracker.database.DatabaseProvider
import com.example.cattracker.database.catsRegistered.CatsRegistered
import com.example.cattracker.database.catsRegistered.CatsRegisteredDao
import com.example.cattracker.database.insulinreadings.InsulinReadingsDao
import com.example.cattracker.graphing.InsulinClassViewModel
import com.example.cattracker.graphing.InsulinScreen
import com.example.cattracker.main.appTopBottomBars.CatAppBottomBar
import com.example.cattracker.main.appTopBottomBars.CatAppTopBar
import com.example.cattracker.main.catSelectionSheet.CatSelectionSheet
import com.example.cattracker.registerCatView.RegisterCatViewModel
import com.example.cattracker.registerCatView.RegisterNewCat

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = DatabaseProvider.getDatabase(applicationContext)
        val insulinPageDao = database.insulinReadingsDao()
        val catRegisterDao = database.catsRegisteredDao()


        setContent {
            CatApp(
                insulinPageDao = insulinPageDao,
                catRegisterDao = catRegisterDao
            )
        }
    }
}
