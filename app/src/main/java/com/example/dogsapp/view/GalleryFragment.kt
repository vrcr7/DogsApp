package com.example.dogsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogsapp.R
class GalleryFragment : Fragment() {
    private lateinit var adapter: RecyclerView.Adapter<GalleryViewHolder>

    private val urlList = listOf(
        "https://images.dog.ceo/breeds/hound-english/n02089973_2551.jpg",
        "https://images.dog.ceo/breeds/hound-english/n02089973_2551.jpg",
        "https://images.dog.ceo/breeds/hound-english/n02089973_2551.jpg"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.listaRecyclerView)

        // Configurar el RecyclerView con un GridLayoutManager
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager

        // Crear el adaptador personalizado
        adapter = object : RecyclerView.Adapter<GalleryViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.gallery_item, parent, false)
                return GalleryViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
                val imageUrl = urlList[position]
                holder.bind(imageUrl)
            }

            override fun getItemCount(): Int = urlList.size
        }

        recyclerView.adapter = adapter

        return view
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageUrl: String) {
            // Aquí puedes implementar la lógica para cargar la imagen utilizando una biblioteca como Glide o Picasso
            // Por ejemplo:
            Glide.with(itemView)
                .load(imageUrl)
                .into(imageView)

            // Establecer el listener para el clic en la imagen
            itemView.setOnClickListener {
                (activity as? MainActivity)?.mostrar(imageUrl)
            }
        }
    }
}

