package com.capstone.cuacatani.ui.main

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.data.WeatherRepository
import com.capstone.cuacatani.data.pref.UserModel
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.response.RegisterResponse
import com.capstone.cuacatani.response.WeatherResponse
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val liveDataResult = MutableLiveData<Result<RegisterResponse>>()
    private val token = MutableLiveData<LoginResult?>()

    fun getSession(
        context: Context
    ): LiveData<LoginResult?> {
        val DataToken = userRepository.getSession(context)
        token.value = DataToken
        return token
    }

    fun getPlants() = userRepository.getPlants()
}

class WeatherViewModel: ViewModel() {
    private val repository: WeatherRepository = WeatherRepository()
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

//    fun fetchWeather(lat: Double, lon: Double) {
//        viewModelScope.launch {
//            val response = repository.get(lat, lon)
//            _weather.value = response
//        }
//    }
}