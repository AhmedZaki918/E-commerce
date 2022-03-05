package com.silkysys.umco.ui.adapter.checkout.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.order.create.Items
import com.silkysys.umco.databinding.LayoutTrackOrderBinding
import com.silkysys.umco.util.setupPicasso

class TrackerViewHolder(val binding: LayoutTrackOrderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    // Bind views
    fun bindPlaceOrder(currentItem: Items) {
        binding.apply {
            tvProductName.text = currentItem.product.name
            tvProductCaption.text = currentItem.product.short_description
            setupPicasso(
                currentItem.product.base_image.original_image_url,
                ivProductImage
            )
            tvProductPrice.text = currentItem.formated_base_price
            tvQuantity.text = currentItem.qty_ordered.toString()
        }
    }

    fun bindGetOrder(currentItem: com.silkysys.umco.data.model.order.get.Items) {
        binding.apply {
            tvProductName.text = currentItem.name
            tvProductCaption.text = currentItem.product.short_description
            setupPicasso(
                currentItem.product.base_image?.original_image_url,
                ivProductImage
            )
            tvProductPrice.text = currentItem.formated_base_price
            tvQuantity.text = currentItem.qty_ordered.toString()
        }
    }
}