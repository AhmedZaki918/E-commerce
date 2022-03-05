package com.silkysys.umco.data.model.auth.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.silkysys.umco.data.local.Constants

@Entity(tableName = Constants.TABLE)
data class Data(
    @PrimaryKey
    val id: Int = 0,
    val gender: String? = "",
    val updated_at: String? = "",
    val phone: String? = "",
    val date_of_birth: String? = "",
    val name: String? = "",
    val last_name: String? = "",
    val created_at: String? = "",
    val first_name: String? = "",
    val email: String? = "",
    val status: Int? = 0
)