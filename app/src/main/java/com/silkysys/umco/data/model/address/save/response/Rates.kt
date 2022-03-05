package com.silkysys.umco.data.model.address.save.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rates(
    val carrierTitle: String? = "",
    val rates: List<RatesItem>?
) : Parcelable