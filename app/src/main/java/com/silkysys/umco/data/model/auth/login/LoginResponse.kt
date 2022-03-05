package com.silkysys.umco.data.model.auth.login

data class LoginResponse(
    val data: Data,
    val message: String = "",
    val token: String = ""
)