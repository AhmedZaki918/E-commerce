package com.silkysys.umco.ui.wishlist

import com.silkysys.umco.data.model.products.byCategory.DataItem

interface OnWishlistDetails {
    fun removeItem(item: DataItem)
}