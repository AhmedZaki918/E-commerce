package com.silkysys.umco.data.model.order.get

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataItem(
    val id: Int? = 0,
    val customer_first_name: String? = "",
    val created_at: String? = "",
    val status_label: String? = "",
    val items: List<Items>,
    val shipping_address: ShippingAddress,
    val payment_title: String? = "",
    val formated_base_sub_total: String? = "",
    val formated_base_shipping_amount: String? = "",
    val formated_base_tax_amount: String? = "",
    val formated_base_grand_total: String? = ""
) : Parcelable