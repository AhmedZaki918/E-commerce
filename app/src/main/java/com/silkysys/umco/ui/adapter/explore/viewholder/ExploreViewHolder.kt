package com.silkysys.umco.ui.adapter.explore.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.categories.all.DataItem
import com.silkysys.umco.databinding.LayoutExploreBinding
import com.silkysys.umco.ui.explore.SelectCategoryActivity
import com.silkysys.umco.util.setupPicasso
import com.silkysys.umco.util.startActivity

class ExploreViewHolder(val binding: LayoutExploreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // Bind data
    fun bind(currentItem: DataItem) {
        binding.apply {
            currentItem.apply {
                tvCategory.text = name
                if (image_url == null) ivCategory.setImageResource(R.drawable.ic_placeholder)
                else setupPicasso(image_url, ivCategory)
                // Click listener on item
                itemView.setOnClickListener {
                    root.context.startActivity(
                        Constants.CATEGORY_ID,
                        currentItem,
                        SelectCategoryActivity::class.java
                    )
                }
            }
        }
    }
}