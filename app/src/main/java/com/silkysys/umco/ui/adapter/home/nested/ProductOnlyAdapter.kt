package com.silkysys.umco.ui.adapter.home.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutProductOnlyBinding
import com.silkysys.umco.ui.adapter.home.viewholder.ProductOnlyViewHolder
import com.silkysys.umco.util.OnItemClick

class ProductOnlyAdapter(
    private val onItemClick: OnItemClick,
    private val dataItem: List<DataItem>,
    val wishlistDao: WishlistDao
) :
    RecyclerView.Adapter<ProductOnlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOnlyViewHolder {
        return ProductOnlyViewHolder(
            onItemClick,
            LayoutProductOnlyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), wishlistDao
        )
    }

    override fun onBindViewHolder(holder: ProductOnlyViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount() = dataItem.size
}