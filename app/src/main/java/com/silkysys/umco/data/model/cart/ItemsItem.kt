package com.silkysys.umco.data.model.cart

data class ItemsItem(
    val id: Int,
    val formated_base_price: String,
    val formated_base_total: String,
    val additional: Additional,
    val product: Product,
)