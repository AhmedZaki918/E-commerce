package com.silkysys.umco.data.model.address.save.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val rates: List<Rates>?,
    val cart: Cart
) : Parcelable