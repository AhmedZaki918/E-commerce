package com.silkysys.umco.ui.adapter.cart

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.cart.ItemsItem
import com.silkysys.umco.databinding.LayoutCartBinding
import com.silkysys.umco.ui.cart.OnCartDetails
import com.silkysys.umco.util.setupPicasso
import com.silkysys.umco.util.toast

class CartViewHolder(
    val binding: LayoutCartBinding,
    private val onCartDetails: OnCartDetails
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    // Variable to store current item passed from bind method
    private var items: ItemsItem? = null

    init {
        binding.ibDelete.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnSub.setOnClickListener(this)
    }

    // Bind data
    fun bind(currentItem: ItemsItem) {
        binding.apply {
            tvProductName.text = currentItem.product.name
            tvPrice.text = currentItem.formated_base_price
            tvTotalPrice.text = currentItem.formated_base_total
            tvQuantity.text = currentItem.additional.quantity.toString()
            setupPicasso(currentItem.product.base_image.original_image_url, ivProduct)
        }
        items = currentItem
    }

    override fun onClick(item: View) {
        Constants.apply {
            when (item.id) {
                R.id.ib_delete -> items?.id?.let { onCartDetails.removeItem(it) }
                R.id.btn_plus -> items?.let {
                    onCartDetails.updateItem(it.product.id, ADD_ONE, binding)
                }
                R.id.btn_sub -> items?.let {
                    binding.root.context.apply {
                        if (it.additional.quantity == MINIMUM_QTY) toast(getString(R.string.minimum_one))
                        else onCartDetails.updateItem(it.product.id, REMOVE_ONE, binding)
                    }
                }
            }
        }
    }
}