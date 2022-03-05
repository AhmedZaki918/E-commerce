package com.silkysys.umco.ui.wishlist

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.silkysys.umco.R
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.FragmentFavouriteBinding
import com.silkysys.umco.ui.adapter.wishlist.WishlistAdapter
import com.silkysys.umco.ui.search.SearchActivity
import com.silkysys.umco.util.BaseFragment
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate),
    View.OnClickListener, OnWishlistDetails {
    private lateinit var viewModel: WishlistViewModel


    override fun FragmentFavouriteBinding.initialize() {
        setClickListeners()
        viewModel = ViewModelProvider(this@WishlistFragment)[WishlistViewModel::class.java]
        viewModel.initGetWishlist().observe(viewLifecycleOwner) {
            val wishlistAdapter = WishlistAdapter(it, this@WishlistFragment)
            if (wishlistAdapter.itemCount == 0) {
                viewModel.showEmptyState(true, this)
            } else binding.rvFavorite.adapter = wishlistAdapter
        }
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.iv_search -> requireContext().startActivity(SearchActivity::class.java)
            R.id.iv_back -> findNavController().navigate(R.id.action_global_home_nav)
            R.id.btn_back_home -> requireActivity().onBackPressed()
            R.id.btn_try_again -> {
                findNavController().apply {
                    popBackStack()
                    navigate(R.id.action_global_explore_nav)
                }
            }
        }
    }

    override fun removeItem(item: DataItem) {
        viewModel.deleteItem(item)
    }

    private fun setClickListeners() {
        binding.apply {
            this@WishlistFragment.apply {
                ivSearch.setOnClickListener(this)
                ivBack.setOnClickListener(this)
                btnTryAgain.setOnClickListener(this)
                btnBackHome.setOnClickListener(this)
            }
        }
    }
}