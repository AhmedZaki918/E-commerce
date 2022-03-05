package com.silkysys.umco.data.repository

import com.silkysys.umco.data.model.payment.request.Payment
import com.silkysys.umco.data.model.payment.request.PaymentRequest
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class CheckoutRepo @Inject constructor(private val apiService: APIService) : SafeApiCall {

    suspend fun saveShippingMethod(shippingMethod: String?) = safeApiCall {
        apiService.saveShipping(true, shippingMethod)
    }

    suspend fun savePaymentMethod(paymentMethod: String?) = safeApiCall {
        val body = PaymentRequest(Payment(paymentMethod))
        apiService.savePayment(true, body)
    }

    suspend fun placeOrder() = safeApiCall {
        apiService.placeOrder(true)
    }

    suspend fun getOrders() = safeApiCall {
        apiService.getAllOrders(true, 0)
    }
}