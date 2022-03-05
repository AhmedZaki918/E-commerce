package com.silkysys.umco.data.model.order.create

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShippingAddress(
    val country: String? = "",
    val city: String? = "",
    val address1: List<String>?,
    val postcode: String? = "",
    val last_name: String? = "",
    val created_at: String? = "",
    val updated_at: String? = "",
    val phone: String? = "",
    val name: String? = "",
    val country_name: String? = "",
    val id: Int? = 0,
    val state: String? = "",
    val first_name: String? = "",
    val email: String? = ""
) : Parcelable