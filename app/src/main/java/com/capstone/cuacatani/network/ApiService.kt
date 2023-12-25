package com.capstone.cuacatani.network

import com.capstone.cuacatani.response.LoginResponse
import com.capstone.cuacatani.response.PlantsResponseItem
import com.capstone.cuacatani.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun signup(
        @Body requestBody: Register
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: Login
    ): LoginResponse

    @GET("plants")
    suspend fun getPlants(): List<PlantsResponseItem>

}