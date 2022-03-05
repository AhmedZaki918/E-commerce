package com.silkysys.umco.data.model.order.get

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val id: Int? = 0,
    val sku: String? = "",
    val name: String? = "",
    val product: Product,
    val qty_ordered: Int? = 0,
    val formated_base_price: String? = ""
) : Parcelable