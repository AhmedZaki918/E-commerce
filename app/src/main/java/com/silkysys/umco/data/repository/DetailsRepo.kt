package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class DetailsRepo @Inject constructor(
    val apiService: APIService,
    val userPreferences: UserPreferences
) : SafeApiCall {

    suspend fun addToCart(id: Int, qty: Int) = safeApiCall {
        apiService.updateCart(id, true, qty, id)
    }

    fun isUserLoggedIn(): Boolean {
        return userPreferences.read(Constants.USER_TOKEN) != ""
    }
}