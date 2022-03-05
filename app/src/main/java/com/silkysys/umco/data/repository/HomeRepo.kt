package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class HomeRepo @Inject constructor(
    val apiService: APIService,
    private val wishlistDao: WishlistDao,
    private val userPreferences: UserPreferences
) : SafeApiCall {

    suspend fun getBanners() = safeApiCall {
        apiService.getSliders()
    }

    suspend fun getProducts() = safeApiCall {
        apiService.getDescendant(0)
    }

    suspend fun addToCart(id: Int, qty: Int) = safeApiCall {
        apiService.updateCart(id, true, qty, id)
    }

    suspend fun addToWishlist(wishlist: DataItem) {
        wishlistDao.addToWishlist(wishlist)
    }

    suspend fun removeFromWishlist(wishlist: DataItem) {
        wishlistDao.deleteItem(wishlist)
    }

    fun isUserLoggedIn(): Boolean {
        return userPreferences.read(Constants.USER_TOKEN) != ""
    }
}