package com.silkysys.umco.ui.adapter.home.viewholder

import android.graphics.Paint
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutProductOnlyBinding
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.OnItemClick
import com.silkysys.umco.util.setupPicasso
import javax.inject.Inject

class ProductOnlyViewHolder @Inject constructor(
    private val onItemClick: OnItemClick,
    private val binding: LayoutProductOnlyBinding,
    private val wishlistDao: WishlistDao
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    // Variable to store current item passed from bind method
    private var currentItem: DataItem? = null


    init {
        binding.btnAdd.setOnClickListener(this)
        binding.ivFavourite.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    // Bind data
    fun bind(dataItem: DataItem) {
        binding.apply {
            if (dataItem.formated_special_price != null) {
                // Show visibility of special price
                tvStockLabel.visibility = VISIBLE
                tvStockLabel.text = dataItem.formated_regular_price
                tvStockLabel.paintFlags = tvStockLabel.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

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
                 ivFavourite.isChecked = wishlistDao.fetchInWishlist(dataItem.name) != null
             }
            tvPrice.text = dataItem.formated_price
            tvProduct.text = dataItem.name
            setupPicasso(dataItem.base_image.original_image_url, ivProduct)
            currentItem = dataItem
        }
    }


    override fun onClick(item: View) {
        when {
            // Add to cart
            item.id == R.id.btn_add -> currentItem?.let {
                onItemClick.addToCart(
                    it.id,
                    it.in_stock
                )
            }
            // Add to wishlist
            item.id == R.id.iv_favourite -> currentItem?.let {
                if (binding.ivFavourite.isChecked) {
                    onItemClick.addOrRemoveWishlist(it, Constants.ADD)
                } else onItemClick.addOrRemoveWishlist(it, Constants.REMOVE)
            }
            // Whole item
            item == itemView -> onItemClick.onItemDetails(currentItem)
        }
    }
}