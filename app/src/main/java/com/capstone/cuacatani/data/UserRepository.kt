package com.capstone.cuacatani.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.network.ApiService
import com.capstone.cuacatani.network.Login
import com.capstone.cuacatani.network.Register
import com.capstone.cuacatani.response.LoginResponse
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.response.PlantsResponseItem
import com.capstone.cuacatani.response.RegisterResponse
import org.json.JSONObject
import retrofit2.HttpException

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<RegisterResponse> {
        return try {
            val response = apiService.signup(Register(username, email, password))
            Result.Success(response)
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()

            val jsonObject = JSONObject(error!!)
            val errorMessage = jsonObject.getString("message")
            Result.Error(errorMessage)
        } catch (e: Exception) {
        Result.Error(e.message.toString())
    }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(Login(email, password))
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun saveSession(loginResult: LoginResult, context: Context) {
        val settingPreferences = UserPreference(context)
        return settingPreferences.putUser(loginResult)
    }

    fun getSession(context: Context): LoginResult? {
        val settingPreferences = UserPreference(context)
        return settingPreferences.gainUser()
    }

    fun getPlants(): LiveData<Result<List<PlantsResponseItem>>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getPlants()
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, pref)
            }.also { instance = it }
    }
}