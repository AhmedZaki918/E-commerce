package com.silkysys.umco.data.model.payment.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(
    val method: String? = "",
    val updated_at: String? = "",
    val created_at: String? = "",
    val id: Int? = 0,
    val method_title: String? = ""
) : Parcelable