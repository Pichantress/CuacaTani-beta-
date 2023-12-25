package com.capstone.cuacatani.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.cuacatani.databinding.BoxPlantsBinding
import com.capstone.cuacatani.model.Plant

class SectionsPagerAdapter(private val newsList: ArrayList<Plant>, private val onItemClick: (Plant) -> Unit) :
    RecyclerView.Adapter<SectionsPagerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BoxPlantsBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivPhoto
        val plant = binding.tvNamePlant
        val panen = binding.tvPanen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BoxPlantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = newsList[position]

        holder.image.setImageResource(plant.titleImage)
        holder.plant.text = plant.titlePlant
        holder.panen.text = plant.titlePanen

        holder.itemView.setOnClickListener {
            onItemClick.invoke(plant)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}