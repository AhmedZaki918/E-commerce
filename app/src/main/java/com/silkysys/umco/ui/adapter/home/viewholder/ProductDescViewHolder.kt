package com.silkysys.umco.ui.adapter.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.categories.descendant.Data
import com.silkysys.umco.databinding.LayoutProductDescBinding
import com.silkysys.umco.util.setupPicasso
import com.silkysys.umco.util.toast

class ProductDescViewHolder(
    private val binding: LayoutProductDescBinding
) : RecyclerView.ViewHolder(binding.root) {
    // Bind data
    fun bind(data: Data) {
        binding.apply {
            tvCategory.text = data.name
            if (data.image_url == null) {
                ivCategory.setImageResource(R.drawable.ic_placeholder)
            } else setupPicasso(data.image_url, ivCategory)
        }
        itemView.setOnClickListener {
            binding.root.context.toast(Constants.DEMO)
        }
    }
}