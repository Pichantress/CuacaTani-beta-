package com.capstone.cuacatani.ui.plants

import androidx.lifecycle.ViewModel
import com.capstone.cuacatani.data.UserRepository

class PlantsViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getPlants() = userRepository.getPlants()

}