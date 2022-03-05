package com.silkysys.umco.data.model.cart

data class Product(
    val formated_price: String = "",
    val is_item_in_cart: Boolean = false,
    val in_stock: Boolean = false,
    val base_image: BaseImage,
    val name: String = "",
    val id: Int = 0,
)