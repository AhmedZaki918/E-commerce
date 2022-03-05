package com.silkysys.umco.data.model.address.create

data class Data(
    val country: String = "",
    val city: String = "",
    val address1: List<String>?,
    val postcode: String = "",
    val last_name: String = "",
    val created_at: String = "",
    val is_default: Boolean = false,
    val updated_at: String = "",
    val phone: String = "",
    val company_name: String = "",
    val country_name: String = "",
    val id: Int = 0,
    val state: String = "",
    val first_name: String = ""
)