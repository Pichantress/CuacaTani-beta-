package com.capstone.cuacatani.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.response.RegisterResponse

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val liveDataResult = MutableLiveData<Result<RegisterResponse>>()
    private val token = MutableLiveData<LoginResult?>()

    fun loginUser(
        email: String, password: String
    ) = userRepository.login(email, password)

    fun getSession(
        context: Context
    ): LiveData<LoginResult?> {
        val DataToken = userRepository.getSession(context)
        token.value = DataToken
        return token
    }

    fun saveSession(
        loginResult: LoginResult, context: Context
    ) = userRepository.saveSession(loginResult, context)

}

