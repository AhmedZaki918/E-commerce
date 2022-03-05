package com.silkysys.umco.data.model.products.byCategory

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.silkysys.umco.data.local.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.WISHLIST_TABLE)
data class DataItem(
    @PrimaryKey
    val id: Int = 0,
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
    val is_saved: Boolean = false,
    val is_item_in_cart: Boolean = false,
    val price: String = "",
    val name: String = "",
    val sku: String = "",
    val formated_special_price: String? = "",
    val formated_regular_price: String? = ""
) : Parcelable