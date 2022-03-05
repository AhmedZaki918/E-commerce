package com.silkysys.umco.ui.adapter.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.order.get.DataItem
import com.silkysys.umco.databinding.LayoutOrdersBinding

class OrdersAdapter(val dataItem: List<DataItem>) :
    RecyclerView.Adapter<OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        return OrdersViewHolder(
            LayoutOrdersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount() = dataItem.size
}