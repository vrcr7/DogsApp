package com.example.dogsapp.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dogsapp.R
import com.example.dogsapp.databinding.ActivityMainBinding

import com.example.dogsapp.model.Dogs
import com.example.dogsapp.model.DogsRoomDatabase
import com.example.dogsapp.model.repository.DogsRepository
import com.example.dogsapp.view.viewmodel.DogsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dogsViewModel: DogsViewModel
    internal var data: MutableLiveData<List<Dogs>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instanciar la base de datos y el DAO
        val database = DogsRoomDatabase.getDatabase(applicationContext)
        val phoneDao = database.DogsDao()
        // Crear una instancia del repositorio pasando el DAO
        val dogsRepository = DogsRepository(phoneDao)
        // Crear una instancia de PhoneViewModel directamente
        dogsViewModel = DogsViewModel(application, dogsRepository)

        dogsViewModel.allDatos.observe(this, Observer { newList ->
            // Actualizar la variable de datos
            data.value = newList
            Log.i("","el valor de la data es")
            Log.i("",data.value.toString())
            val newFragment = ListaFragment()
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainer, newFragment)
                .commit()

        })


    }
    fun mostrar(raza: String) {
        Toast.makeText(this, "Estamos en el main $raza", Toast.LENGTH_SHORT).show()
        Log.i("","estoy en el main")

        // Crear instancia del GalleryFragment y pasar el argumento "raza"
        val galleryFragment = GalleryFragment().apply {
            arguments = Bundle().apply {
                putString("raza", raza)
            }
        }

        // Reemplazar el fragmento actual con el GalleryFragment y agregar el fragmento anterior a la pila de retroceso
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, galleryFragment)
            .addToBackStack(null)
            .commit()
    }

}