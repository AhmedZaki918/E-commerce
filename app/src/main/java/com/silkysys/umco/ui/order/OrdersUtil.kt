package com.silkysys.umco.ui.order

import android.content.Context
import android.widget.Button
import androidx.core.content.ContextCompat.getColor
import com.silkysys.umco.R
import com.silkysys.umco.data.model.order.create.Order
import com.silkysys.umco.data.model.order.get.DataItem

class OrdersUtil(val context: Context) {

    fun editStyle(firstButton: Button, secondButton: Button) {
        firstButton.apply {
            setTextColor(getColor(context, R.color.colorWhite))
            setBackgroundResource(R.drawable.button_history_custom)
        }
        secondButton.apply {
            setTextColor(getColor(context, R.color.colorBlack))
            setBackgroundResource(0)
        }
    }


    fun formatDeliveryAddress(address: String, model: Any?): String {
        var deliveryAddress: String
        val formattedAddress =
            address.substring(address.indexOf("=") + 1, address.lastIndex)
        // Check model type
        if (model is Order) {
            // Concatenate the address, city and country in one line
            model.apply {
                deliveryAddress = formattedAddress + ", " + this.shipping_address?.city +
                        ", " + this.shipping_address?.country_name
            }
        } else {
            model as DataItem
            // Concatenate the address, city and country in one line
            deliveryAddress = formattedAddress + ", " + model.shipping_address.city +
                    ", " + model.shipping_address.country_name
        }
        return deliveryAddress
    }
}