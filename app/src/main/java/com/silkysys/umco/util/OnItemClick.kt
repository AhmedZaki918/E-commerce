package com.silkysys.umco.util

import com.silkysys.umco.data.model.products.byCategory.DataItem

interface OnItemClick {
    fun addToCart(id: Int, inStock: Boolean)
    fun addOrRemoveWishlist(item: DataItem, operation: String)
    fun <T> onItemDetails(model: T)
}