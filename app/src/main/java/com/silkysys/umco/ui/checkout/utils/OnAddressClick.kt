package com.silkysys.umco.ui.checkout.utils

import com.silkysys.umco.data.model.address.all.DataItem

interface OnAddressClick {
    fun onEditAddress(dataItem: DataItem)
    fun onAddressSelected(dataItem: DataItem)
}