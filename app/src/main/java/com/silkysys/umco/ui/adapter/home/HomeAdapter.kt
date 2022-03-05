package com.silkysys.umco.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.categories.descendant.Data
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.databinding.LayoutHomeBinding
import com.silkysys.umco.ui.adapter.home.viewholder.HomeViewHolder
import com.silkysys.umco.util.OnItemClick

class HomeAdapter(
    val data: List<Data>,
    private val apiService: APIService,
    private val onItemClick: OnItemClick,
    val wishlistDao: WishlistDao
) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            onItemClick,
            apiService,
            LayoutHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),wishlistDao
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}