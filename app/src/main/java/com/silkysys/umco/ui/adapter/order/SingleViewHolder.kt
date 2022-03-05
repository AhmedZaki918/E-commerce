package com.silkysys.umco.ui.adapter.order

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.order.get.Items
import com.silkysys.umco.databinding.LayoutSingleOrderBinding
import com.silkysys.umco.util.setupPicasso

class SingleViewHolder(val binding: LayoutSingleOrderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // Bind data
    fun bind(currentItem: Items) {
        binding.apply {
            setupPicasso(
                currentItem.product.base_image?.original_image_url,
                ivProduct
            )
            tvTitle.text = currentItem.name
            tvPrice.text = currentItem.formated_base_price
            tvQuantity.text = currentItem.qty_ordered.toString()
        }
    }
}