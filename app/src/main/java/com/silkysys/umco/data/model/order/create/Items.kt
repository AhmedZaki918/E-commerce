package com.silkysys.umco.data.model.order.create

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val id: Int? = 0,
    val product: Product,
    val formated_base_price: String? = "",
    val qty_ordered: Int? = 0
) : Parcelable
