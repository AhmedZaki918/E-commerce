package com.silkysys.umco.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.silkysys.umco.data.model.products.byCategory.DataItem

@Dao
interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishlist(wishlist: DataItem)

    @Query("SELECT * FROM Wishlist")
    fun getWishlist(): LiveData<List<DataItem>>

    @Query("SELECT * FROM Wishlist WHERE name = :name")
    suspend fun fetchInWishlist(name: String?): DataItem

    @Delete
    suspend fun deleteItem(wishlist: DataItem)
}