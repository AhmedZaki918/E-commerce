package com.silkysys.umco.data.model.order.get

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val formated_price: String? = "",
    val base_image: BaseImage?,
    val name: String? = "",
    val id: Int? = 0,
    val sku: String? = "",
    val short_description: String? = ""
) : Parcelable