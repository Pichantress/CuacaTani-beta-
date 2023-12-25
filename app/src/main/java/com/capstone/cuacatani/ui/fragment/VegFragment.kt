package com.capstone.cuacatani.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cuacatani.R
import com.capstone.cuacatani.model.Plant
import com.capstone.cuacatani.ui.detail.DetailActivity

class VegFragment : Fragment() {

    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var plantArrayList: ArrayList<Plant>

    private lateinit var imageId: Array<Int>
    private lateinit var plantId: Array<String>
    private lateinit var panenId: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_stories)
        recyclerView.layoutManager =layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = SectionsPagerAdapter(plantArrayList) { clickedPlant ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("PLANT_ID", clickedPlant.titlePlant)
            intent.putExtra("PANEN_ID", clickedPlant.titlePanen)
            intent.putExtra("IMAGE_ID", clickedPlant.titleImage)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    private fun dataInitialize(){
        plantArrayList = arrayListOf<Plant>()

        imageId = arrayOf(
            R.drawable.kacang_panjang,
            R.drawable.kacang_polong,
            R.drawable.buncis
        )

        plantId = arrayOf(
            getString(R.string.veg_1),
            getString(R.string.veg_2),
            getString(R.string.veg_3)
        )

        panenId = arrayOf(
            getString(R.string.c_veg),
            getString(R.string.c_veg),
            getString(R.string.c_veg)
        )

        for (i in imageId.indices){
            val plant = Plant(imageId[i], plantId[i], panenId[i])
            plantArrayList.add(plant)
        }
    }
}