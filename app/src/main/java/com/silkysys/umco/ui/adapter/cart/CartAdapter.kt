package com.silkysys.umco.ui.adapter.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.cart.ItemsItem
import com.silkysys.umco.databinding.LayoutCartBinding
import com.silkysys.umco.ui.cart.OnCartDetails

class CartAdapter(private val items: List<ItemsItem>, private val onCartDetails: OnCartDetails) :
    RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onCartDetails
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}