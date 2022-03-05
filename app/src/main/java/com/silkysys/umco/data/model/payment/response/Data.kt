package com.silkysys.umco.data.model.payment.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(val cart: Cart) : Parcelable