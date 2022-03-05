package com.silkysys.umco.ui.adapter.checkout.delivery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.model.DeliveryDay
import com.silkysys.umco.databinding.LayoutWeekdaysBinding

class DeliveryDayAdapter(private var weekdays: List<DeliveryDay>) :
    RecyclerView.Adapter<DeliveryDayAdapter.DaysViewHolder>() {

    private var selectedDay: String? = null
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        return DaysViewHolder(
            LayoutWeekdaysBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(weekdays[position])
    }

    override fun getItemCount() = weekdays.size

    inner class DaysViewHolder(val binding: LayoutWeekdaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Bind data
        fun bind(currentItem: DeliveryDay) {
            binding.apply {
                tvDay.text = currentItem.day
                // Swap colors of background and text
                root.context.apply {
                    if (selectedDay.equals(currentItem.day)) {
                        cvDay.setCardBackgroundColor(getColor(this, R.color.primaryButton))
                        tvDay.setTextColor(getColor(this, R.color.colorWhite))
                    } else {
                        cvDay.setCardBackgroundColor(getColor(this, R.color.colorWhite))
                        tvDay.setTextColor(getColor(this, R.color.colorBlack))
                    }
                }
                cvDay.setOnClickListener {
                    if (selectedPosition != -1) notifyItemChanged(selectedPosition)
                    selectedDay = currentItem.day
                    selectedPosition = adapterPosition
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}