package com.silkysys.umco.data.model.address.save.request

data class Shipping(
    val address1: Address,
    val address_id: Int = 0,
    val last_name: String = "",
    val first_name: String = "",
    val email: String = ""
)