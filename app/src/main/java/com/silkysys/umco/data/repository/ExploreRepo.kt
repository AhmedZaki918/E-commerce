package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class ExploreRepo @Inject constructor(
    val apiService: APIService,
    val userPreferences: UserPreferences,
    val wishlistDao: WishlistDao
) : SafeApiCall {

    suspend fun getCategories() = safeApiCall {
        apiService.getAllCategories(0)
    }

    suspend fun getSpecificCategory(id: Int) = safeApiCall {
        apiService.getProductsByCategory(1, id)
    }

    suspend fun addToCart(id: Int, qty: Int) = safeApiCall {
        apiService.updateCart(id, true, qty, id)
    }

    fun isUserLoggedIn(): Boolean {
        return userPreferences.read(Constants.USER_TOKEN) != ""
    }

    suspend fun addToWishlist(wishlist: DataItem) {
        wishlistDao.addToWishlist(wishlist)
    }

    suspend fun removeFromWishlist(wishlist: DataItem) {
        wishlistDao.deleteItem(wishlist)
    }
}