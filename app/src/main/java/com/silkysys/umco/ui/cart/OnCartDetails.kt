package com.silkysys.umco.ui.cart

import com.silkysys.umco.databinding.LayoutCartBinding

interface OnCartDetails {
    fun removeItem(id: Int)
    fun updateItem(id: Int, operation: Int, binding: LayoutCartBinding)
}