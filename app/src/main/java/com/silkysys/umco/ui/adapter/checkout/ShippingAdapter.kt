package com.silkysys.umco.ui.adapter.checkout

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.address.save.response.Rates
import com.silkysys.umco.data.model.address.save.response.RatesItem
import com.silkysys.umco.databinding.LayoutShippingMethodBinding
import com.silkysys.umco.ui.checkout.utils.OnShippingClick

class ShippingAdapter(
    private val ratesItem: List<Rates>,
    private val onShippingClick: OnShippingClick
) : RecyclerView.Adapter<ShippingAdapter.ShippingViewHolder>() {

    // To handle visibility of right sign icon
    private var selectedShipping: String? = null
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingViewHolder {
        return ShippingViewHolder(
            LayoutShippingMethodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onShippingClick
        )
    }

    override fun onBindViewHolder(holder: ShippingViewHolder, position: Int) {
        holder.bind(ratesItem[position].rates?.get(0))
    }

    override fun getItemCount() = ratesItem.size

    inner class ShippingViewHolder(
        val binding: LayoutShippingMethodBinding,
        private val onShippingClick: OnShippingClick
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: RatesItem?) {
            binding.apply {
                // Bind views
                tvShippingMethod.text = currentItem?.carrier_title
                tvPrice.text = currentItem?.formated_base_price

                // Edit visibility of right sign icon
                ivSelectedIcon.apply {
                    visibility =
                        if (selectedShipping.equals(currentItem?.carrier_title)) VISIBLE else INVISIBLE
                }

                // Click listener on item
                itemView.setOnClickListener {
                    // Update right sign icon location on each click
                    if (selectedPosition != -1) notifyItemChanged(selectedPosition)
                    selectedShipping = currentItem?.carrier_title
                    selectedPosition = adapterPosition
                    notifyItemChanged(adapterPosition)
                    // Pass selected shipping method to shipping activity
                    if (currentItem?.method != null) {
                        onShippingClick.onMethodSelected(currentItem.method)
                    }
                }
            }
        }
    }
}