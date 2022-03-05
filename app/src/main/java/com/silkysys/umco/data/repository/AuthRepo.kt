package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserDao
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import com.silkysys.umco.util.Coroutines
import javax.inject.Inject

class AuthRepo @Inject constructor(
    val apiService: APIService,
    private val userDao: UserDao,
    private val userPreferences: UserPreferences
) : SafeApiCall {

    suspend fun login(email: String, password: String) = safeApiCall {
        apiService.login(true, email, password)
    }

    suspend fun logout() = safeApiCall {
        apiService.logout(true)
    }

    suspend fun getUser() = safeApiCall {
        apiService.getUser(true)
    }

    fun deleteUserInfo() {
        userPreferences.apply {
            Constants.apply {
                if (read(USER_TOKEN) != "") {
                    remove(USER_TOKEN)
                    Coroutines.background { userDao.deleteUser() }
                }
            }
        }
    }
}