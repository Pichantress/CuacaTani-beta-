package com.capstone.cuacatani.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.cuacatani.R
import com.capstone.cuacatani.databinding.ActivityDetailBinding
import com.capstone.cuacatani.response.PlantsResponseItem

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Plant"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val plant: PlantsResponseItem? = intent.getParcelableExtra(EXTRA_PLANT)

        if (plant != null) {
            binding.tvTitle.text = plant.name
            binding.tvPanen.text = plant.category

            Glide.with(this).load(plant.image).skipMemoryCache(true).into(binding.imgPlant)

            binding.tvReqText.text = getString(R.string.reqText)
            binding.tvHowText.text = getString(R.string.howText)
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_PLANT = "extra_plant"
    }
}