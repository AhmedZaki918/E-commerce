package com.silkysys.umco.data.model.shipping

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val methods: List<MethodsItem>?
) : Parcelable