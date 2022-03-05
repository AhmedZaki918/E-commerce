package com.silkysys.umco.ui.home

import android.view.View
import android.view.View.GONE
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.categories.descendant.DescendantResponse
import com.silkysys.umco.data.model.sliders.DataItem
import com.silkysys.umco.data.model.sliders.SlidersResponse
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.FragmentHomeBinding
import com.silkysys.umco.ui.adapter.home.BannerAdapter
import com.silkysys.umco.ui.adapter.home.HomeAdapter
import com.silkysys.umco.ui.detail.SingleProductActivity
import com.silkysys.umco.ui.search.SearchActivity
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    View.OnClickListener, OnItemClick {

    private lateinit var bannerUtils: BannerUtils
    private lateinit var viewModel: HomeViewModel
    private var bannerItems: ArrayList<DataItem> = ArrayList()

    @Inject
    lateinit var apiService: APIService

    @Inject
    lateinit var wishlistDao: WishlistDao

    override fun FragmentHomeBinding.initialize() {
        initViews()
        // Create api request for banners, products
        viewModel.initBanners()
        viewModel.initProducts()
        // Observe changes on api response via live data
        viewModel.bannerResponse.observe(viewLifecycleOwner) { response ->
            setupBanner(response)
        }
        viewModel.productsResponse.observe(viewLifecycleOwner) { response ->
            setupProducts(response)
        }
        // Register callback on Banners
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bannerUtils.setColors(position)
                super.onPageSelected(position)
            }
        })
    }


    override fun onClick(item: View) {
        val id = item.id
        if (id == R.id.iv_search) requireContext().startActivity(SearchActivity::class.java)
    }


    override fun addToCart(id: Int, inStock: Boolean) {
        if (viewModel.checkUserToken()) {
            if (inStock) {
                Coroutines.background {
                    val response = viewModel.initAddToCart(id, Constants.ADD_ONE)
                    Coroutines.main {
                        when (response) {
                            is Resource.Success -> requireContext().toast(response.value.message)
                            is Resource.Failure -> handleApiError(response)
                        }
                    }
                }
            } else requireContext().toast(getString(R.string.empty_stock))
        } else requireContext().toast(getString(R.string.register))
    }


    override fun addOrRemoveWishlist(
        item: com.silkysys.umco.data.model.products.byCategory.DataItem,
        operation: String
    ) {
        if (operation == Constants.ADD) {
            viewModel.addToWishlist(item)
            requireContext().toast(Constants.ITEM_ADDED)
        } else {
            viewModel.removeFromWishlist(item)
            requireContext().toast(Constants.ITEM_REMOVED)
        }
    }

    override fun <T> onItemDetails(model: T) {
        // Go to details screen for a specific item
        requireContext().startActivity(
            Constants.PRODUCT_DETAILS,
            model as com.silkysys.umco.data.model.products.byCategory.DataItem,
            SingleProductActivity::class.java
        )
    }


    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.ivSearch.setOnClickListener(this)
        binding.ivNotification.setOnClickListener(this)
    }


    private fun setupBanner(response: Resource<SlidersResponse>) {
        binding.apply {
            progressBarBanner.visibility = GONE
            when (response) {
                is Resource.Success -> {
                    // Rearrange banners in right order when come back to this fragment
                    bannerItems.clear()
                    bannerItems = response.value.data as ArrayList<DataItem>
                    // Display banners on screen via utils class
                    bannerUtils = BannerUtils(
                        requireContext(),
                        arrayOfNulls(BannerAdapter(bannerItems).itemCount)
                    )
                    viewPager.adapter = BannerAdapter(bannerItems)
                    bannerUtils.setIndicators(dotsContainer)
                }
                is Resource.Failure -> handleApiError(response)
            }
        }
    }


    private fun setupProducts(response: Resource<DescendantResponse>) {
        binding.progressBarCategory.visibility = GONE
        when (response) {
            is Resource.Success -> {
                binding.rvCategory.adapter =
                    response.value.data?.let {
                        HomeAdapter(
                            it,
                            apiService,
                            this@HomeFragment,
                            wishlistDao
                        )
                    }
            }
            is Resource.Failure -> handleApiError(response)
        }
    }
}