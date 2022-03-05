package com.silkysys.umco.data.model.order.create

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id: Int?,
    val status_label: String? = "",
    val customer_first_name: String? = "",
    val customer_last_name: String? = "",
    val shipping_description: String? = "",
    val payment_title: String? = "",
    val shipping_address: ShippingAddress?,
    val formated_base_grand_total: String? = "",
    val formated_base_sub_total: String? = "",
    val formated_base_discount_amount: String? = "",
    val formated_base_tax_amount: String? = "",
    val formated_base_shipping_amount: String? = "",
    val items: List<Items>,
    val created_at: String? = ""
) : Parcelable