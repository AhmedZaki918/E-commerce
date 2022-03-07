package com.silkysys.umco.ui.detail

import androidx.lifecycle.ViewModel
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.repository.DetailsRepo
import com.silkysys.umco.databinding.ActivitySingleProductBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repo: DetailsRepo) : ViewModel() {


    // Create post request for add to cart
    suspend fun initAddToCart(id: Int, qty: Int) = repo.addToCart(id, qty)

    fun editOnQuantity(binding: ActivitySingleProductBinding, operation: String = "") {
        binding.apply {
            // Increase quantity by one
            if (operation == Constants.ADD) {
                // Enable clickable on sub button if it's false only
                if (!btnSub.isClickable) {
                    btnSub.isClickable = true
                }
                var qty = tvQuantity.text.toString().toInt()
                qty++
                tvQuantity.text = qty.toString()

            } else {
                // Decrease quantity by one
                var qty = tvQuantity.text.toString().toInt()
                if (qty == 1) {
                    btnSub.isClickable = false
                } else {
                    qty--
                    tvQuantity.text = qty.toString()
                }
            }
        }
    }

    // Check user is logged in or not
    fun checkUserToken() = repo.isUserLoggedIn()
}