package com.silkysys.umco.data.model.address.save.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    val id: Int,
    val billing_address : BillingAddress,
    val shipping_address : ShippingAddress
) : Parcelable
