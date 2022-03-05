package com.silkysys.umco.data.model.order.create

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int? = 0,
    val name: String? = "",
    val short_description: String? = "",
    val base_image: BaseImage
) : Parcelable