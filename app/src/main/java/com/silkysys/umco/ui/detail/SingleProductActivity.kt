package com.silkysys.umco.ui.detail

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivitySingleProductBinding
import com.silkysys.umco.ui.explore.alertGoToCart
import com.silkysys.umco.ui.main.MainActivity
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleProductActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySingleProductBinding
    private lateinit var viewModel: DetailsViewModel
    var data: DataItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        updateUi(intent.getParcelableExtra(Constants.PRODUCT_DETAILS))
        setClickListeners()
    }


    override fun onClick(item: View) {
        val id = item.id
        Constants.apply {
            when (id) {
                // Add to cart
                R.id.btn_add -> data?.let { addToCart(it) }
                R.id.btn_go_to_cart -> openFragment(CART)
                R.id.btn_plus -> viewModel.editOnQuantity(binding, ADD)
                R.id.btn_sub -> viewModel.editOnQuantity(binding)
                // Go to previous screen
                else -> onBackPressed()
            }
        }
    }


    fun addToCart(data: DataItem) {
        if (viewModel.checkUserToken()) {
            if (data.in_stock) {
                binding.apply {
                    val qty = tvQuantity.text.toString().toInt()
                    btnAdd.visibility = INVISIBLE
                    progressBar.visibility = VISIBLE
                    Coroutines.background {
                        val response = viewModel.initAddToCart(data.id, qty)
                        Coroutines.main {
                            btnAdd.visibility = VISIBLE
                            progressBar.visibility = INVISIBLE
                            when (response) {
                                is Resource.Success -> {
                                    alertGoToCart {
                                        goToCart { openFragment(Constants.CART) }
                                        continueShopping { openFragment(Constants.EXPLORE) }
                                        cancelDialog()
                                    }.show()
                                }
                                is Resource.Failure -> handleApiError(response)
                            }
                        }
                    }
                }
            } else toast(getString(R.string.empty_stock))
        } else toast(getString(R.string.register))
    }


    private fun setClickListeners() {
        binding.apply {
            this@SingleProductActivity.apply {
                btnAdd.setOnClickListener(this)
                btnGoToCart.setOnClickListener(this)
                ivBack.setOnClickListener(this)
                btnPlus.setOnClickListener(this)
                btnSub.setOnClickListener(this)
            }
        }
    }


    private fun updateUi(dataItem: DataItem?) {
        binding.apply {
            data = dataItem
            dataItem?.apply {
                setupPicasso(base_image.original_image_url, ivProduct)
                tvProductName.text = name
                tvPrice.text = formated_price
                // This's product caption is for test only
                tvCaption.text = getString(R.string.dummy)
            }
        }
    }


    private fun openFragment(fragmentName: String) {
        startActivity(
            Constants.OPEN_FRAGMENT,
            fragmentName,
            MainActivity::class.java
        )
    }
}