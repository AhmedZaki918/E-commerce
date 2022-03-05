package com.silkysys.umco.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.categories.all.AllCategoriesResponse
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.ExploreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repo: ExploreRepo) : ViewModel() {

    val categoriesResponse = MutableLiveData<Resource<AllCategoriesResponse>>()

    // Create get request to get all categories
    fun initGetCategories() {
        viewModelScope.launch {
            categoriesResponse.value = repo.getCategories()
        }
    }

    // Create get request to get a specific category
    suspend fun initSpecificCategory(id: Int) = repo.getSpecificCategory(id)

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