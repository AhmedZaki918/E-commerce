package com.silkysys.umco.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.categories.descendant.DescendantResponse
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.model.sliders.SlidersResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepo) :
    ViewModel() {

    // Mutable live data to observe changes on fragment
    val bannerResponse = MutableLiveData<Resource<SlidersResponse>>()
    val productsResponse = MutableLiveData<Resource<DescendantResponse>>()

    // Create get request for banners
    fun initBanners() = viewModelScope.launch {
        bannerResponse.value = repo.getBanners()
    }

    // Create get request for get all products
    fun initProducts() = viewModelScope.launch {
        productsResponse.value = repo.getProducts()
    }

    // Create post request for add to cart
    suspend fun initAddToCart(id: Int, qty: Int) = repo.addToCart(id, qty)

    // Add wishlist item to database
    fun addToWishlist(wishlist: DataItem) {
        viewModelScope.launch {
            repo.addToWishlist(wishlist)
        }
    }

    fun removeFromWishlist(wishlist: DataItem) {
        viewModelScope.launch {
            repo.removeFromWishlist(wishlist)
        }
    }

    // Check user is logged in or not
    fun checkUserToken() = repo.isUserLoggedIn()
}