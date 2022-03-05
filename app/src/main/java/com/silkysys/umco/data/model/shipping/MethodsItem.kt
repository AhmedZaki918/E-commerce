package com.silkysys.umco.data.model.shipping

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MethodsItem(
    val method: String? = "",
    val description: String? = "",
    val sort: Int? = 0,
    val method_title: String? = ""
) : Parcelable