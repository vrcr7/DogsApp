package com.example.dogsapp.model.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.dogsapp.model.Dogs
import com.example.dogsapp.model.DogsDao
import com.example.dogsapp.model.remote.RetrofitClient
import com.example.dogsapp.model.remote.clases.DogsApiClass

class DogsRepository (private val dogsDao:DogsDao){
    private val networkService = RetrofitClient.retrofitInstance()

    val allDatos: LiveData<List<Dogs>> = dogsDao.getAllDatos()


    @WorkerThread
    suspend fun insert(dogs: Dogs) {
        dogsDao.insert(dogs)
    }

    @WorkerThread
    suspend fun deleteAll() {
        dogsDao.deleteAll()
    }
  /*  @WorkerThread
    suspend fun deleteAll() {
        dogsDao.deleteAll()
    }
  */
    //Agregamos los datos desde la Api

    suspend fun fetchDogs() {
        val response = kotlin.runCatching { networkService.fetchDogList() }

        response.onSuccess {
            if (it.isSuccessful) {
                val dogsApiResponse = it.body()
                dogsApiResponse?.let { dogsApiResponse ->
                    val dogsList = fromInternetToDogsEntity(dogsApiResponse)
                    Log.d("","estamos llegando aqui")
                    Log.d("Repository", "dogsList: $dogsList")
                    dogsDao.insertAllDogs(dogsList)
                }
            } else {
                Log.d("Repo", "${it.code()}-${it.errorBody()}")
                Log.d("","estamos llegando al else, el Onsuccess falla")
            }
        }

        response.onFailure {
            Log.e("Error onFailure", "el error onfailure es ${it.message}")
            Log.d("","estamos llegando al else, en el onFailure")
        }
    }






}