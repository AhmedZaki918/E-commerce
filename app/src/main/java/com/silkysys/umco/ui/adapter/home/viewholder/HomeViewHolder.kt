package com.silkysys.umco.ui.adapter.home.viewholder

import android.view.View.INVISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.categories.descendant.Data
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.databinding.LayoutHomeBinding
import com.silkysys.umco.ui.adapter.home.nested.ProductAndDescAdapter
import com.silkysys.umco.ui.adapter.home.nested.ProductOnlyAdapter
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.OnItemClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewHolder(
    private val onItemClick: OnItemClick,
    private val apiService: APIService,
    val binding: LayoutHomeBinding,
    val wishlistDao: WishlistDao
) : RecyclerView.ViewHolder(binding.root) {

    var data: Data? = null

    init {
        binding.rvHome.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    // Bind data to views
    fun bind(currentItem: Data) {
        binding.tvHeader.text = currentItem.name
        data = currentItem
        Coroutines.background {
            // Product and description layout
            if (currentItem.display_mode == Constants.PRODUCT_AND_DESC) {
                val response = apiService.getDescendant(currentItem.id)
                // Set adapter on main thread
                withContext(Dispatchers.Main) {
                    binding.progressBarHome.visibility = INVISIBLE
                    binding.rvHome.adapter = response.data?.let { ProductAndDescAdapter(it) }
                }
            } else {
                // Product only layout
                val response = apiService.getProductsByCategory(1, currentItem.id)
                // Set adapter on main thread
                withContext(Dispatchers.Main) {
                    binding.progressBarHome.visibility = INVISIBLE
                    binding.rvHome.adapter =
                        response.data?.let { ProductOnlyAdapter(onItemClick, it,wishlistDao) }
                }
            }
        }
    }
}