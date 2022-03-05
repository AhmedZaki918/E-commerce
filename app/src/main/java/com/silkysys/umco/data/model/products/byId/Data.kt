package com.silkysys.umco.data.model.products.byId

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val formated_price: String = "",
    val short_description: String = "",
    val show_quantity_changer: Boolean = false,
    val description: String = "",
    val created_at: String = "",
    val type: String = "",
    val in_stock: Boolean = false,
    val url_key: String = "",
    val is_wishlisted: Boolean = false,
    val base_image: BaseImage,
    val updated_at: String = "",
    val reviews: Reviews,
    val is_saved: Boolean = false,
    val is_item_in_cart: Boolean = false,
    val price: String = "",
    val name: String = "",
    val id: Int = 0,
    val sku: String = ""
) : Parcelable