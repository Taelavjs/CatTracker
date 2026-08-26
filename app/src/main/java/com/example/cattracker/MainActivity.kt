package com.example.cattracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cattracker.database.DatabaseProvider
import com.example.cattracker.graphing.InsulinClassViewModel
import com.example.cattracker.graphing.InsulinScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InsulinTrackingPage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsulinTrackingPage() {
    val context = LocalContext.current

    val database = remember {
        DatabaseProvider.getDatabase(context)
    }

    val dao = database.insulinReadingsDao()

    val insulinScreenViewModel: InsulinClassViewModel = viewModel {
        InsulinClassViewModel(dao)
    }

    var showInsulinTextReadings by remember { mutableStateOf(true) }
    var showAddInsulinDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cat Tracker")
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        showInsulinTextReadings = !showInsulinTextReadings
                    },
                    icon = {
                        Text(
                            if (showInsulinTextReadings)
                                "Graph"
                            else
                                "Readings"
                        )
                    },
                    label = {
                        Text("Insulin")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        showAddInsulinDialog = true
                    },
                    icon = {
                        Text("+")
                    },
                    label = {
                        Text("Add Reading")
                    }
                )
            }
        }
    ) { innerPadding ->

        InsulinScreen(
            modifier = Modifier.padding(innerPadding),
            viewModel = insulinScreenViewModel,
            showInsulinTextReadings = showInsulinTextReadings,
            showAddInsulinDialog = showAddInsulinDialog,
            onShowAddInsulinDialogChange = {
                showAddInsulinDialog = it
            }
        )
    }
}
