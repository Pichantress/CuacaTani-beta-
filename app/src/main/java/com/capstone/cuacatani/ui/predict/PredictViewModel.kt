package com.capstone.cuacatani.ui.predict

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.data.WeatherRepository
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.response.RegisterResponse
import com.capstone.cuacatani.response.WeatherResponse

class PredictViewModel(private val userRepository: UserRepository) : ViewModel() {
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