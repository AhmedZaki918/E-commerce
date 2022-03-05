package com.silkysys.umco.data.model.order.create

import android.os.Parcelable
import com.silkysys.umco.data.model.order.create.Order
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceOrderResponse(
    val success: String? = "",
    val order: Order
) : Parcelable
