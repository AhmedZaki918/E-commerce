package com.silkysys.umco.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.products.byCategory.DataItem
import com.silkysys.umco.databinding.ActivitySingleProductBinding
import com.silkysys.umco.ui.explore.alertGoToCart
import com.silkysys.umco.ui.main.MainActivity
import com.silkysys.umco.util.setupPicasso
import com.silkysys.umco.util.startActivity

class SingleProductActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySingleProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUi(intent.getParcelableExtra(Constants.PRODUCT_DETAILS))
        setClickListeners()
    }


    override fun onClick(item: View) {
        val id = item.id
        Constants.apply {
            when (id) {
                // Add to cart
                R.id.btn_add -> {
                    // Open alert dialog
                    alertGoToCart {
                        goToCart { openFragment(CART) }
                        continueShopping { openFragment(EXPLORE) }
                        cancelDialog()
                    }.show()
                }
                // Go to cart
                R.id.btn_go_to_cart -> openFragment(CART)
                // Go to previous screen
                else -> onBackPressed()
            }
        }
    }


    private fun setClickListeners() {
        binding.apply {
            this@SingleProductActivity.apply {
                btnAdd.setOnClickListener(this)
                btnGoToCart.setOnClickListener(this)
                ivBack.setOnClickListener(this)
            }
        }
    }


    private fun updateUi(dataItem: DataItem?) {
        binding.apply {
            if (dataItem != null) {
                setupPicasso(dataItem.base_image.original_image_url, ivProduct)
                tvProductName.text = dataItem.name
                tvPrice.text = dataItem.formated_price
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