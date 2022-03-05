package com.silkysys.umco.data.model.address.save.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatesItem(
    val id: Int = 0,
    val carrier: String = "",
    val carrier_title: String = "",
    val method: String = "",
    val method_title: String = "",
    val method_description: String = "",
    val price: Int = 0,
    val formated_price: String = "",
    val base_price: Int = 0,
    val formated_base_price: String = ""
) : Parcelable



