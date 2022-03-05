package com.silkysys.umco.ui.adapter.home.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.categories.descendant.Data
import com.silkysys.umco.databinding.LayoutProductDescBinding
import com.silkysys.umco.ui.adapter.home.viewholder.ProductDescViewHolder

class ProductAndDescAdapter(val data: List<Data>) :
    RecyclerView.Adapter<ProductDescViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductDescViewHolder {
        return ProductDescViewHolder(
            LayoutProductDescBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductDescViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}