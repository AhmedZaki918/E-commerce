package com.silkysys.umco.data.model.categories.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataItem(
    val display_mode: String? = "",
    val image_url: String? = "",
    val description: String? = "",
    val created_at: String? = "",
    val updated_at: String? = "",
    val name: String? = "",
    val id: Int? = 0,
    val slug: String? = "",
    val status: Int? = 0
) : Parcelable