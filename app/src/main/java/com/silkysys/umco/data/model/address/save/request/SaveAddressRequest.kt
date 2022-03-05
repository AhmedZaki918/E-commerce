package com.silkysys.umco.data.model.address.save.request

data class SaveAddressRequest(
    val shipping: Shipping,
    val billing: Billing
)