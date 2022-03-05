package com.silkysys.umco.ui.checkout.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.order.get.AllOrdersResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.CheckoutRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repo: CheckoutRepo) : ViewModel() {

    val ordersResponse = MutableLiveData<Resource<AllOrdersResponse>>()

    // Create post request to save shipping method
    suspend fun initSaveShipping(shippingMethod: String?) =
        repo.saveShippingMethod(shippingMethod)

    // Create post request to save payment method
    suspend fun initSavePayment(paymentMethod: String?) =
        repo.savePaymentMethod(paymentMethod)

    // Create post request to place order
    suspend fun initPlaceOrder() = repo.placeOrder()

    // Create get request to get all orders
    fun initGetOrders() = viewModelScope.launch {
        ordersResponse.value = repo.getOrders()
    }
}