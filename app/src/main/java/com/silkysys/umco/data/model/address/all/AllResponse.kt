package com.silkysys.umco.data.model.address.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllResponse(val data: List<DataItem>?) : Parcelable