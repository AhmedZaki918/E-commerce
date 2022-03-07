package com.silkysys.umco.ui.explore

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.WishlistDao
import com.silkysys.umco.data.model.categories.all.AllCategoriesResponse
import com.silkysys.umco.data.model.categories.all.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivitySelectCategoryBinding
import com.silkysys.umco.ui.adapter.explore.AllCategoriesAdapter
import com.silkysys.umco.ui.adapter.explore.CategoryAdapter
import com.silkysys.umco.ui.detail.SingleProductActivity
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectCategoryActivity : AppCompatActivity(), View.OnClickListener, OnItemClick {

    private lateinit var binding: ActivitySelectCategoryBinding
    private lateinit var viewModel: ExploreViewModel

    @Inject
    lateinit var wishlistDao: WishlistDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ExploreViewModel::class.java]
        viewModel.initGetCategories()
        viewModel.categoriesResponse.observe(this) { response ->
            updateAllCategories(response)
        }
        updateSelectedCategory(intent.getParcelableExtra(Constants.CATEGORY_ID))
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        if (item.id == R.id.iv_back) onBackPressed()
    }


    override fun addToCart(id: Int, inStock: Boolean) {
        if (viewModel.checkUserToken()) {
            if (inStock) {
                Coroutines.background {
                    val response = viewModel.initAddToCart(id, Constants.ADD_ONE)
                    Coroutines.main {
                        when (response) {
                            is Resource.Success -> toast(response.value.message)
                            is Resource.Failure -> handleApiError(response)
                        }
                    }
                }
            } else toast(getString(R.string.empty_stock))
        } else toast(getString(R.string.register))
    }


    override fun addOrRemoveWishlist(
        item: com.silkysys.umco.data.model.products.byCategory.DataItem,
        operation: String
    ) {
        if (operation == Constants.ADD) {
            viewModel.addToWishlist(item)
            toast(Constants.ITEM_ADDED)
        } else {
            viewModel.removeFromWishlist(item)
            toast(Constants.ITEM_REMOVED)
        }
    }


    override fun <T> onItemDetails(model: T) {
        // Go to details screen for a specific item
        if (model is com.silkysys.umco.data.model.products.byCategory.DataItem) {
            startActivity(
                Constants.PRODUCT_DETAILS,
                model,
                SingleProductActivity::class.java
            )
        } else {
            model as DataItem
            binding.apply {
                rvResults.visibility = INVISIBLE
                progressBarCategory.visibility = VISIBLE
                updateSelectedCategory(model)
            }
        }
    }


    private fun updateSelectedCategory(currentItem: DataItem?) {
        binding.apply {
            Coroutines.background {
                val response = currentItem?.id?.let { viewModel.initSpecificCategory(it) }
                Coroutines.main {
                    progressBarCategory.visibility = INVISIBLE
                    rvResults.visibility = VISIBLE
                    if (response is Resource.Success) {
                        val category = response.value.data
                        if (category != null) {
                            tvCategoryName.text = currentItem.name
                            rvResults.adapter =
                                CategoryAdapter(category, this@SelectCategoryActivity, wishlistDao)
                        }
                    } else handleApiError(response as Resource.Failure)
                }
            }
        }
    }


    private fun updateAllCategories(response: Resource<AllCategoriesResponse>?) {
        binding.apply {
            progressBarSlider.visibility = INVISIBLE
            if (response is Resource.Success) {
                val categories = response.value.data
                if (categories != null) {
                    // Setup adapter
                    rvSelectCategory.layoutManager = LinearLayoutManager(
                        binding.root.context,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    // Based on title only(true or false) we display required layout
                    rvSelectCategory.adapter =
                        AllCategoriesAdapter(categories, this@SelectCategoryActivity)
                }
            } else if (response is Resource.Failure) handleApiError(response)
        }
    }
}