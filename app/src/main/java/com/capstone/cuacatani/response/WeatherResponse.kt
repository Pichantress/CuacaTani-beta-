package com.capstone.cuacatani.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@SerializedName("weather")
	val weatherList: List<Weather>,

	@SerializedName("main")
	val main: Main,

	@SerializedName("sys")
	val sys: Sys,

	@SerializedName("dt")
	val date: Long
)

data class Main(

	@SerializedName("temp")
	val temp: Double,

	@SerializedName("humidity")
	val humidity: Double,

	@SerializedName("pressure")
	val pressure: Double,

)

data class Weather(
	@SerializedName("main")
	val main: String,

	@SerializedName("description")
	val description: String
)

data class Sys(

	@SerializedName("country")
	val country: String? = null,
)
