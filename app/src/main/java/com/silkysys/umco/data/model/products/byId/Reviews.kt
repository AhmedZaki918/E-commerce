package com.silkysys.umco.data.model.products.byId

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reviews(
    val total: Int = 0,
    val total_rating: Int = 0,
    val average_rating: Int = 0
) : Parcelable