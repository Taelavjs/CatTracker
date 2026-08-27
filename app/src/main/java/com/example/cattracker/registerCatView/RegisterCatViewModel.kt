package com.example.cattracker.registerCatView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cattracker.database.catsRegistered.CatsRegistered
import com.example.cattracker.database.catsRegistered.CatsRegisteredDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RegisterCatViewModel(
    private val dao: CatsRegisteredDao
): ViewModel() {
    val cats: Flow<List<CatsRegistered>> = dao.getAllCats()

    fun registerNewCat(name: String) {
        viewModelScope.launch {
            dao.insert(CatsRegistered(name = name))
        }
    }

}