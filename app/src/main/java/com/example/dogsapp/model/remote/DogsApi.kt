package com.example.dogsapp.model.remote

import com.example.dogsapp.model.remote.clases.DogsApiClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {
        // listado de dogs
        @GET("breeds/list/all")
        suspend fun fetchDogList(): Response<DogsApiClass>
}