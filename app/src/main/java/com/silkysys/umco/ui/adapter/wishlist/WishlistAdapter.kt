package com.silkysys.umco.ui.adapter.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutFavoriteBinding
import com.silkysys.umco.ui.wishlist.OnWishlistDetails

class WishlistAdapter(
    val data: List<DataItem>,
    private val onWishlistDetails: OnWishlistDetails
) :
    RecyclerView.Adapter<WishlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        return WishlistViewHolder(
            LayoutFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onWishlistDetails
        )
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}