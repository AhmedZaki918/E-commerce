package com.silkysys.umco.ui.adapter.order

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.order.get.DataItem
import com.silkysys.umco.databinding.LayoutOrdersBinding
import com.silkysys.umco.ui.order.TrackOrderActivity
import com.silkysys.umco.util.startActivity

class OrdersViewHolder(val binding: LayoutOrdersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // Bind data
    fun bind(currentItem: DataItem) {
        binding.apply {
            // Order id
            val orderId = root.context.getString(R.string.order_label) + " " + currentItem.id
            tvOrderId.text = orderId
            // Order date
            val date = currentItem.created_at
            val formattedDate =
                root.context.getString(R.string.placed_on) + " " + date?.substring(
                    0,
                    date.indexOf("T")
                )
            tvOrderDate.text = formattedDate
            // List of orders
            rvNested.layoutManager = LinearLayoutManager(
                root.context,
                LinearLayoutManager.HORIZONTAL, false
            )
            rvNested.adapter = SingleOrderAdapter(currentItem.items)

            // Click listeners
            btnViewDetails.setOnClickListener {
                root.context.startActivity(
                    Constants.GET_ORDER,
                    currentItem,
                    TrackOrderActivity::class.java
                )
            }
        }
    }
}