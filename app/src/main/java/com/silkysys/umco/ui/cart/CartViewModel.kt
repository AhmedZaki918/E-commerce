package com.silkysys.umco.ui.cart

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.cart.DetailsResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.CartRepo
import com.silkysys.umco.databinding.FragmentCartBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo: CartRepo) : ViewModel() {

    val cartDetailsResponse = MutableLiveData<Resource<DetailsResponse>>()

    // Create get request to display all list in cart
    fun initCartDetails() = viewModelScope.launch {
        cartDetailsResponse.value = repo.getCartDetails()
    }

    // Create get request to remove from cart
    suspend fun initRemoveItem(id: Int) = repo.removeItem(id)

    // Create post request to add to cart
    suspend fun initUpdateItem(id: Int, qty: Int) = repo.updateItem(id, qty)

    // Display empty state
    fun isEmptyCart(status: Boolean, binding: FragmentCartBinding) {
        binding.apply {
            if (status) {
                // Display empty state
                clEmptyState.visibility = VISIBLE
                rvCart.visibility = GONE
                clTotalCart.visibility = GONE
            } else {
                // Fill cart
                clTotalCart.visibility = VISIBLE
                clEmptyState.visibility = GONE
                rvCart.visibility = VISIBLE
            }
        }
    }
}