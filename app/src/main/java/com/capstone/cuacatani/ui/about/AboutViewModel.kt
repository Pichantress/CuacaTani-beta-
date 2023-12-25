package com.capstone.cuacatani.ui.about

import android.content.Context
import androidx.lifecycle.ViewModel
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.response.LoginResult

class AboutViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun saveSession(
        loginResult: LoginResult, context: Context
    ) = userRepository.saveSession(loginResult, context)
}
