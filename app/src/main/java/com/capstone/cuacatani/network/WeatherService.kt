package com.capstone.cuacatani.network

import com.capstone.cuacatani.model.WeatherApp
import com.capstone.cuacatani.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getWeather(
        @Query("q") city:String,
        @Query("appid") appid: String,
        @Query("units") units: String,
    ): Call<WeatherApp>
}