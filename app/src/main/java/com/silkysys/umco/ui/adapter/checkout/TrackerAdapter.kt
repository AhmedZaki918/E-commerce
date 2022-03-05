package com.silkysys.umco.ui.adapter.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.order.create.Items
import com.silkysys.umco.databinding.LayoutTrackOrderBinding
import com.silkysys.umco.ui.adapter.checkout.viewholder.TrackerViewHolder

class TrackerAdapter(
    private val placeOrder: List<Items>?,
    private val getOrder: List<com.silkysys.umco.data.model.order.get.Items>?
) : RecyclerView.Adapter<TrackerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerViewHolder {
        return TrackerViewHolder(
            LayoutTrackOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackerViewHolder, position: Int) {
        if (placeOrder != null) holder.bindPlaceOrder(placeOrder[position])
        else {
            if (getOrder != null) holder.bindGetOrder(getOrder[position])
        }
    }

    override fun getItemCount(): Int {
        return when {
            placeOrder?.size != null -> placeOrder.size
            getOrder?.size != null -> getOrder.size
            else -> 0
        }
    }
}