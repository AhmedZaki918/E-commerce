package com.silkysys.umco.data.model.address.save.request

data class Billing(
    val address1: Address,
    val address_id: Int = 0,
    val last_name: String = "",
    val use_for_shipping: String = "",
    val first_name: String = "",
    val email: String = ""
)