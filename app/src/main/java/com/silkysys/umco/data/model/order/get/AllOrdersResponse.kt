package com.silkysys.umco.data.model.order.get

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllOrdersResponse(
    val data: List<DataItem>?
) : Parcelable