package com.silkysys.umco.data.model.products.byCategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseImage(
    val large_image_url: String = "",
    val original_image_url: String = "",
    val small_image_url: String = "",
    val medium_image_url: String = ""
) : Parcelable

