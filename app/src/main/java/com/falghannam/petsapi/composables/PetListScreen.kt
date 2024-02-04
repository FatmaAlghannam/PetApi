package com.falghannam.petsapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falghannam.petsapi.viewModel.PetViewModel

@Composable
fun PetListScreen(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = viewModel()
) {
    val pets = viewModel.pets
    Column {
        Text(text = viewModel.message)

        LazyColumn(modifier = modifier) {
            items(pets) { pet ->
                PetItem(pet, viewModel)
            }
        }
    }
}