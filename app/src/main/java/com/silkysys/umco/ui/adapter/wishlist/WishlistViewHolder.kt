package com.silkysys.umco.ui.adapter.wishlist

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutFavoriteBinding
import com.silkysys.umco.ui.wishlist.OnWishlistDetails
import com.silkysys.umco.util.setupPicasso

class WishlistViewHolder(
    val binding: LayoutFavoriteBinding,
    private val onWishlistDetails: OnWishlistDetails
) : RecyclerView.ViewHolder(binding.root) {

    // Bind data
    fun bind(currentItem: DataItem) {
        binding.apply {
            currentItem.apply {
                setupPicasso(base_image.original_image_url, ivProduct)
                tvTitle.text = name
                tvPrice.text = price
                tvDate.text = created_at
                // Click listener
                ibDelete.setOnClickListener { onWishlistDetails.removeItem(currentItem) }
            }
        }
    }
}
