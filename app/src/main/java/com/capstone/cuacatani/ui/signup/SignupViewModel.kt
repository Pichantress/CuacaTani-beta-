package com.capstone.cuacatani.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cuacatani.data.Result
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.response.RegisterResponse
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository): ViewModel() {
    private val liveDataResult = MutableLiveData<Result<RegisterResponse>>()

    fun signupUser(
        username: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> {
        viewModelScope.launch {
            val result = userRepository.register(username, email, password)
            liveDataResult.value = result
        }
        return liveDataResult
    }
}