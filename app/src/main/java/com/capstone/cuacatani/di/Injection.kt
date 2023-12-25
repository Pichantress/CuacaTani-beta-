package com.capstone.cuacatani.di

import android.content.Context
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, pref)
    }
}