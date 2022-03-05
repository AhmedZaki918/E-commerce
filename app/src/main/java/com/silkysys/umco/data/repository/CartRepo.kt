package com.silkysys.umco.data.repository

import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class CartRepo @Inject constructor(val apiService: APIService) : SafeApiCall {

    suspend fun getCartDetails() = safeApiCall {
        apiService.getCartDetails(true)
    }

    suspend fun removeItem(id: Int) = safeApiCall {
        apiService.removeProduct(id,true)
    }

    suspend fun updateItem(id: Int, qty: Int) = safeApiCall {
        apiService.updateCart(id, true, qty, id)
    }
}