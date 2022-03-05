package com.silkysys.umco.data.model.categories.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllCategoriesResponse(val data: List<DataItem>?) : Parcelable