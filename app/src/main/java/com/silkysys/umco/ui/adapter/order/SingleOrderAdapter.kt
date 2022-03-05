package com.silkysys.umco.ui.adapter.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.order.get.Items
import com.silkysys.umco.databinding.LayoutSingleOrderBinding

class SingleOrderAdapter(val items: List<Items>) :
    RecyclerView.Adapter<SingleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        return SingleViewHolder(
            LayoutSingleOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}