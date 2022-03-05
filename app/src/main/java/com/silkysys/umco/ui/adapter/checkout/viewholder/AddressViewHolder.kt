package com.silkysys.umco.ui.adapter.checkout.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.databinding.LayoutAddressBinding
import com.silkysys.umco.ui.checkout.utils.OnAddressClick

class AddressViewHolder(
    val binding: LayoutAddressBinding,
    private val onAddressClick: OnAddressClick
) :
    RecyclerView.ViewHolder(binding.root) {
    // Bind data
    fun bind(currentItem: DataItem) {
        binding.apply {
            tvAddress.text = currentItem.address1?.get(0)
            tvCity.text = currentItem.city
            tvCountry.text = currentItem.country_name
            tvMobileNumber.text = currentItem.phone
            // Click listener on item
            itemView.setOnClickListener { onAddressClick.onAddressSelected(currentItem) }
            // Click listener on edit address
            ivEditAddress.setOnClickListener { onAddressClick.onEditAddress(currentItem) }
        }
    }
}