package com.silkysys.umco.data.model.payment.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedShippingRate(
    val formated_price: String? = "",
    val carrier: String? = "",
    val method: String? = "",
    val updated_at: String? = "",
    val method_description: String? = "",
    val price: Int? = 0,
    val carrier_title: String? = "",
    val base_price: Int? = 0,
    val created_at: String? = "",
    val id: Int? = 0,
    val formated_base_price: String? = "",
    val method_title: String? = ""
) : Parcelable