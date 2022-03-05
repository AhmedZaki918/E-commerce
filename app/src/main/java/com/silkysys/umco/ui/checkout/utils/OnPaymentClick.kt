package com.silkysys.umco.ui.checkout.utils

import com.silkysys.umco.data.model.shipping.MethodsItem

interface OnPaymentClick {
    fun onItemClicked(methodItem: MethodsItem?)
}