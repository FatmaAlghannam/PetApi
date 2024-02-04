package com.falghannam.petsapi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.falghannam.petsapi.model.Pet
import com.falghannam.petsapi.viewModel.PetViewModel


@Composable
fun PetItem(pet: Pet, petViewModel: PetViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Column {
                AsyncImage(
                    model = pet.image, contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(55.dp)),
                )

                Button(onClick = {
                    petViewModel.deletPet(pet.id)
                    petViewModel.fetchPets()
                }) {
                    Text("Delete")
                }
            }


            Spacer(modifier = Modifier.width(20.dp))
            Column {

                Text(
                    text = "Name:" + pet.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text ="ID:" + pet.id.toString(),
                    fontSize = 20.sp
                )
                Text(
                    text = "Adopted:" + pet.adopted.toString(),
                    fontSize = 20.sp
                )
                Text(
                    text = "Gender:" + pet.gender,
                    fontSize = 20.sp
                )


            }


        }

    }
}