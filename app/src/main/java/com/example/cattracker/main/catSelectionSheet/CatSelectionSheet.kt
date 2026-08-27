package com.example.cattracker.main.catSelectionSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cattracker.database.catsRegistered.CatsRegistered
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatSelectionSheet(
    cats: List<CatsRegistered>,
    onCatSelected: (CatsRegistered) -> Unit,
    onRegisterNewCat: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        cats.forEach { cat ->
            SelectActiveCatRow(
                cat = cat,
                setActiveCat = onCatSelected
            )
        }

        TextButton(
            onClick = onRegisterNewCat,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("+ Register new cat")
        }
    }
}

@Composable
fun SelectActiveCatRow(
    cat : CatsRegistered,
    setActiveCat: (cat : CatsRegistered) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(10.dp)
            .clickable(true, onClick = {
                setActiveCat(cat)
            }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = cat.name)
        Icon(
            imageVector = Icons.Filled.Pets,
            contentDescription = cat.name
        )
    }
}
