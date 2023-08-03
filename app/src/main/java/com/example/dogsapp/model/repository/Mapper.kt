package com.example.dogsapp.model.repository

import com.example.dogsapp.model.Dogs
import com.example.dogsapp.model.remote.DogsApi
import com.example.dogsapp.model.remote.clases.DogsApiClass
import okhttp3.Response


fun fromInternetToDogsEntity(dogApiResponse: DogsApiClass): List<Dogs> {
    val breedNames = dogApiResponse.message.keys
    return breedNames.map { breedName ->
        Dogs(id = null, breedName = breedName)
    }
}



