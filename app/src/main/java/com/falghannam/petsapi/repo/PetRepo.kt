package com.falghannam.petsapi.repo

import com.falghannam.petsapi.interfaceApi.PetApiService

class PetRepo(private val api: PetApiService) {
    suspend fun getAllPets() = api.getAllPets()
}