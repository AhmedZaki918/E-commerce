package com.silkysys.umco.ui.adapter.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.databinding.LayoutAddressBinding
import com.silkysys.umco.ui.adapter.checkout.viewholder.AddressViewHolder
import com.silkysys.umco.ui.checkout.utils.OnAddressClick

class AddressAdapter(
    val dataItem: List<DataItem>,
    private val onAddressClick: OnAddressClick
) :
    RecyclerView.Adapter<AddressViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            LayoutAddressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onAddressClick
        )
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount() = dataItem.size
}