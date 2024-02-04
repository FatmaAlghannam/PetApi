package com.falghannam.petsapi.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falghannam.petsapi.interfaceApi.PetApiService
import com.falghannam.petsapi.model.Pet
import com.falghannam.petsapi.repo.PetRepo
import com.falghannam.petsapi.singelton.RetrofitHelper
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val petApiService = RetrofitHelper.getInstance().create(PetApiService::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())
    var message by mutableStateOf("")
    init {
        fetchPets()
    }

    fun fetchPets(){
        viewModelScope.launch {
            try {
                pets = repository.getAllPets()
                message = "PetViewModel Fetched All pets successfully"
            } catch (e: Exception){
                // print error message
                message = "PetViewModel Error fetching pets: ${e.message}"

            }
        }
    }

    fun addPet(pet: Pet){
        viewModelScope.launch {
            try {
                val response = petApiService.addPet(pet = pet)
                if (response.isSuccessful && response.body() != null) {
                    message = "PetViewModel Successfully added a pet with ID: ${response.body()!!.id}"
                } else {
                    message = "PetViewModel Failed to add pet: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception){
                message = "PetViewModel Error adding pet: ${e.message}"
            }
        }
    }

    fun deletPet(petID: Int){
        viewModelScope.launch {
            try {
                petApiService.deletePet(petID)
                message = "PetViewModel Successfully deleted a pet with ID: $petID"
            } catch (e: Exception){
                message = "PetViewModel Can't delete pet with ID: $petID, please try again. Error: ${e.message}"
            }
        }
    }
}