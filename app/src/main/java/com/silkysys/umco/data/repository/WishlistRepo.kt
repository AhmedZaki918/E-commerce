package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import javax.inject.Inject

class WishlistRepo @Inject constructor(private val wishlistDao: WishlistDao) {

    fun getWishlist() = wishlistDao.getWishlist()

    suspend fun addToWishlist(wishlist: DataItem) {
        wishlistDao.addToWishlist(wishlist)
    }

    suspend fun deleteItem(wishlist: DataItem) {
        wishlistDao.deleteItem(wishlist)
    }
}