package com.silkysys.umco.ui.adapter.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.categories.all.DataItem
import com.silkysys.umco.databinding.LayoutExploreBinding
import com.silkysys.umco.ui.adapter.explore.viewholder.ExploreViewHolder

class ExploreAdapter(
    val data: List<DataItem>
) : RecyclerView.Adapter<ExploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(
            LayoutExploreBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}