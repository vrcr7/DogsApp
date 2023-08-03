package com.example.dogsapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogsapp.R
import com.example.dogsapp.model.Dogs

class DogsListAdapter(private val datos: MutableLiveData<List<Dogs>>) : RecyclerView.Adapter<DogsListAdapter.DogsViewHolder>() {

    private var mostrarButtonClickListener: ((String) -> Unit)? = null

    fun setMostrarButtonClickListener(listener: (String) -> Unit) {
        mostrarButtonClickListener = listener
    }

    fun setDeleteButtonClickListener(listener: (String) -> Unit) {
        // Corregir el nombre de la variable
        mostrarButtonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return DogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val currentDatos = datos.value?.get(position)
        holder.bind(currentDatos)
    }
    override fun getItemCount(): Int = datos.value?.size ?: 0
    inner class DogsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var razaMostrar: String = ""
        private val listTaButton: TextView = view.findViewById(R.id.listaRaza)

        fun bind(datos: Dogs?) {
            datos?.let {
                listTaButton.text = it.breedName
                razaMostrar = datos.breedName
                Log.i("","la raza para el adapter es : ")
                Log.i("","$razaMostrar")

                listTaButton.setOnClickListener {
                    mostrarButtonClickListener?.invoke(razaMostrar)
                }

            }
        }
    }
}