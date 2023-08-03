package com.example.dogsapp.view

import android.os.Bundle
import com.example.dogsapp.databinding.ActivityMainBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("UNREACHABLE_CODE")
class ListaFragment : Fragment() {
    private lateinit var adapter: DogsListAdapter


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.listaRecyclerView)

        adapter = DogsListAdapter((activity as MainActivity).data)
        recyclerView.adapter = adapter

        adapter.setMostrarButtonClickListener { raza ->
            (activity as MainActivity).mostrar(raza)
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        return view
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}