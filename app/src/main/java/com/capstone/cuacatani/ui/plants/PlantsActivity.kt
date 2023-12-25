package com.capstone.cuacatani.ui.plants

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.Result
import com.capstone.cuacatani.data.UserAdapter
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.databinding.ActivityPlantsBinding
import com.capstone.cuacatani.response.PlantsResponseItem
import com.capstone.cuacatani.ui.about.AboutActivity
import com.capstone.cuacatani.ui.detail.DetailActivity
import com.capstone.cuacatani.ui.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlantsActivity : AppCompatActivity() {

    private val viewModel by viewModels<PlantsViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityPlantsBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager

        bindUIComponents()
        handleTabButtonPress()

        bottomNavigationView.selectedItemId = R.id.navigation_plant

        observePlants()
        supportActionBar?.hide()
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.nav_view)
    }
    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_plant -> {
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_about -> {
                    loadActivities(AboutActivity())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

    private fun observePlants() {
        viewModel.getPlants().observe(this) { result ->
            when (result) {
                is Result.Success -> displayPlants(result.data)
                is Result.Error -> showError(result.error)
                else -> {}
            }
        }
    }

    private fun displayPlants(plants: List<PlantsResponseItem>?) {
        if (plants != null) {
            val adapter = UserAdapter { user ->
            }
            adapter.submitList(plants)
            binding.rvList.adapter = adapter
        }
    }


    private fun showError(errorMessage: String) {
        Toast.makeText(this, "Gagal: $errorMessage", Toast.LENGTH_SHORT).show()
        Log.d("Error", errorMessage)
    }
}
