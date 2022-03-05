package com.silkysys.umco.ui.adapter.explore.viewholder

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutCategoryBinding
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.OnItemClick
import com.silkysys.umco.util.setupPicasso

class CategoryViewHolder(
    val binding: LayoutCategoryBinding,
    val onItemClick: OnItemClick,
    val wishlistDao: WishlistDao
) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    // Variable to store current item passed from bind method
    private var dataItem: DataItem? = null


    init {
        binding.btnAdd.setOnClickListener(this)
        binding.cbFavourite.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    // Bind data
    fun bind(currentItem: DataItem) {
        binding.apply {
            currentItem.apply {
                if (formated_special_price != null) {
                    // Show visibility of special price
                    tvStockLabel.visibility = View.VISIBLE
                    tvStockLabel.text = formated_regular_price
                    tvStockLabel.paintFlags =
                        tvStockLabel.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    // Set custom margin to price text view
                    val param = tvPrice.layoutParams as ViewGroup.MarginLayoutParams
                    param.bottomMargin = 4
                    tvPrice.layoutParams = param

                    // Set custom margin to product image
                    val paramProduct = ivProduct.layoutParams as ViewGroup.MarginLayoutParams
                    paramProduct.topMargin = 8
                    ivProduct.layoutParams = paramProduct
                }

                Coroutines.background {
                    @Suppress("SENSELESS_COMPARISON")
                    cbFavourite.isChecked = wishlistDao.fetchInWishlist(currentItem.name) != null
                }
                tvPrice.text = formated_price
                tvProduct.text = name
                setupPicasso(base_image.original_image_url, ivProduct)
            }
            dataItem = currentItem
        }
    }

    override fun onClick(item: View) {
        when {
            // Add to cart
            item.id == R.id.btn_add -> dataItem?.let {
                onItemClick.addToCart(
                    it.id,
                    it.in_stock
                )
            }
            // Add to wishlist
            item.id == R.id.cb_favourite -> dataItem?.let {
                if (binding.cbFavourite.isChecked) {
                    onItemClick.addOrRemoveWishlist(it, Constants.ADD)
                } else onItemClick.addOrRemoveWishlist(it, Constants.REMOVE)
            }
            // Whole item
            item == itemView -> onItemClick.onItemDetails(dataItem)
        }
    }
}