package com.silkysys.umco.ui.wishlist

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.repository.WishlistRepo
import com.silkysys.umco.databinding.FragmentFavouriteBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(private val repo: WishlistRepo) : ViewModel() {


    // Get all wishlist from local database
    fun initGetWishlist() = repo.getWishlist()


    fun addToWishlist(wishlist: DataItem) {
        viewModelScope.launch {
            repo.addToWishlist(wishlist)
        }
    }

    fun deleteItem(wishlist: DataItem) {
        viewModelScope.launch {
            repo.deleteItem(wishlist)
        }
    }

    // Show empty state if there's no wishlist available
    fun showEmptyState(status: Boolean, binding: FragmentFavouriteBinding) {
        binding.apply {
            if (status) {
                rvFavorite.visibility = INVISIBLE
                clEmptyState.visibility = VISIBLE
            } else {
                rvFavorite.visibility = VISIBLE
                clEmptyState.visibility = INVISIBLE
            }
        }
    }
}