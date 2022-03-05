package com.silkysys.umco.data.model.payment.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    val items_qty: String? = "",
    val billing_address: BillingAddress?,
    val formated_base_sub_total: String? = "",
    val formated_base_grand_total: String? = "",
    val customer_last_name: String? = "",
    val shipping_method: String? = "",
    val payment: Payment?,
    val id: Int? = 0,
    val shipping_address: ShippingAddress?,
    val formated_base_discount: String? = "",
    val customer_first_name: String? = "",
    val formated_base_tax_total: String? = "",
    val selected_shipping_rate: SelectedShippingRate?,
    val items_count: Int? = 0,
    val customer_email: String? = "",
) : Parcelable