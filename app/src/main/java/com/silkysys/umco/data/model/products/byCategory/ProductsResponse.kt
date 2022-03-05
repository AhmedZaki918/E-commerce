package com.silkysys.umco.data.model.products.byCategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(
    val data: List<DataItem>?
) : Parcelable