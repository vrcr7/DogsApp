package com.example.dogsapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.model.Dogs
import com.example.dogsapp.model.DogsRoomDatabase
import com.example.dogsapp.model.repository.DogsRepository
import com.example.dogsapp.view.MainActivity
import kotlinx.coroutines.launch

class DogsViewModel(application: Application, private var repository: DogsRepository) : ViewModel() {

    init{
        val bd= DogsRoomDatabase.getDatabase(application)
        val  centroFuturoDao = bd.DogsDao()
        repository = DogsRepository(centroFuturoDao)
        // lama el método del respositorio
        viewModelScope.launch {
            repository.deleteAll()
            repository.fetchDogs()
        //repository.deleteAll()
          /*  val dog = Dogs(id = null, breedName = "Labrador") // Crea un objeto Dogs con los datos que deseas insertar
            repository.insert(dog)*/
        }
    }

    // Devuelve todos los datos de la base de datos
    val allDatos: LiveData<List<Dogs>> = repository.allDatos
}