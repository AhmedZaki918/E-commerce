package com.silkysys.umco.ui.adapter.checkout.delivery

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.DeliveryTime
import com.silkysys.umco.databinding.LayoutTimeBinding
import com.silkysys.umco.ui.adapter.checkout.delivery.DeliveryTimeAdapter.TimeViewHolder

class DeliveryTimeAdapter(private var deliveryTime: List<DeliveryTime>) :
    RecyclerView.Adapter<TimeViewHolder>() {

    private var selectedTime: String? = null
    private var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder(
            LayoutTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.binding.apply {

            val currentItem = deliveryTime[position]
            tvDeliveryTime.text = currentItem.availableTime

            // Edit visibility of red arrow on card view
            ivTimeSelected.apply {
                visibility = if (selectedTime.equals(currentItem.availableTime)) VISIBLE
                else INVISIBLE
            }
            // Click listener on items
            cvTimeTable.setOnClickListener {
                if (selectedPosition != -1) notifyItemChanged(selectedPosition)
                selectedTime = currentItem.availableTime
                selectedPosition = holder.adapterPosition
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount() = deliveryTime.size
    class TimeViewHolder(val binding: LayoutTimeBinding) : RecyclerView.ViewHolder(binding.root)
}