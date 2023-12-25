package com.capstone.cuacatani.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cuacatani.databinding.BoxListPlantBinding
import com.capstone.cuacatani.response.PlantsResponseItem
import com.capstone.cuacatani.ui.detail.DetailActivity

class UserAdapter(param: (PlantsResponseItem) -> Unit) : ListAdapter<PlantsResponseItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = BoxListPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }
    class MyViewHolder(val binding: BoxListPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: PlantsResponseItem){
            binding.tvNamePlant.text = review.name
            binding.tvPanen.text = review.category
            Glide.with(itemView.context)
            .load(review.image)
            .into(binding.ivPhoto)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_PLANT, review)
                itemView.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlantsResponseItem>() {
            override fun areItemsTheSame(oldItem: PlantsResponseItem, newItem: PlantsResponseItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: PlantsResponseItem, newItem: PlantsResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
