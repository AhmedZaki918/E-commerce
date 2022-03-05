package com.silkysys.umco.ui.adapter.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.LayoutCategoryBinding
import com.silkysys.umco.ui.adapter.explore.viewholder.CategoryViewHolder
import com.silkysys.umco.util.OnItemClick

class CategoryAdapter(
    private val dataItem: List<DataItem>,
    val onItemClick: OnItemClick,
    val wishlistDao: WishlistDao
) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onItemClick,
            wishlistDao
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount() = dataItem.size
}