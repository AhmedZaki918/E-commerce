package com.silkysys.umco.ui.checkout.summary

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.payment.response.PaymentResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityOrderSummaryBinding
import com.silkysys.umco.ui.checkout.utils.CheckoutViewModel
import com.silkysys.umco.ui.checkout.utils.alertPromoCode
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSummaryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOrderSummaryBinding
    private lateinit var viewModel: CheckoutViewModel
    private var promoCode: String? = null
    private var response: PaymentResponse? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        response = intent.getParcelableExtra(Constants.PLACE_ORDER)
        // Display order summary
        displayTotalItems()
        displayDeliveryAddress()
        displayRemaining()
        // Set click listeners
        binding.cvPromotionCode.setOnClickListener(this)
        binding.btnPlaceOrder.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.cv_promotion_code -> {
                alertPromoCode {
                    confirmButton {
                        promoCode = getPromoCode()
                    }
                    cancelButton()
                }.show()
            }
            R.id.btn_place_order -> placeOrder()
            R.id.iv_back -> onBackPressed()
        }
    }


    private fun displayTotalItems() {
        // Display total items by getting itemsQty at first
        response?.data?.cart.apply {
            val itemsQty = this?.items_qty.toString()
            // Prepare total items to display it
            val totalItems = this?.items_count.toString() + " " +
                    getString(R.string.product_total_text) + " " +
                    // then format quantity from ex: 7.000 to 7 by removing this char"."
                    itemsQty.substring(0, itemsQty.indexOf(".")) + " " +
                    getString(R.string.quantity_label)
            // Display total items
            binding.tvCaptionItems.text = totalItems
        }
    }


    private fun displayDeliveryAddress() {
        // Get the address and format it
        response?.data?.cart.apply {
            val address = this?.billing_address?.address1?.get(0)
            val formattedAddress = address?.substring(
                address.indexOf("=") + 1,
                address.lastIndex
            )
            // Concatenate the address, city and country, then store it in delivery address
            val deliveryAddress =
                formattedAddress + ", " + this?.billing_address?.city +
                        ", " + this?.billing_address?.country_name
            // display delivery address
            binding.tvCaptionDeliveryAddress.text = deliveryAddress
        }
    }


    // Display remaining views of order summary
    private fun displayRemaining() {
        response?.data?.cart.apply {
            binding.tvCaptionShippingMethod.text = this?.selected_shipping_rate?.carrier_title
            binding.tvCaptionPaymentMethod.text = this?.payment?.method_title
            // Display the bill
            binding.tvSubtotal.text = this?.formated_base_sub_total
            binding.tvDeliveryPrice.text = this?.selected_shipping_rate?.formated_base_price
            binding.tvTotalTax.text = this?.formated_base_tax_total
            binding.tvGrandTotal.text = this?.formated_base_grand_total
        }
    }


    private fun placeOrder() {
        binding.apply {
            btnPlaceOrder.visibility = INVISIBLE
            progressBar.visibility = VISIBLE
            Coroutines.background {
                val response = viewModel.initPlaceOrder()
                Coroutines.main {
                    if (response is Resource.Success) {
                        startActivity(
                            Constants.PLACE_ORDER,
                            response.value.copy(),
                            CheckoutActivity::class.java
                        )
                        finish()
                    } else if (response is Resource.Failure) {
                        progressBar.visibility = INVISIBLE
                        btnPlaceOrder.visibility = VISIBLE
                        handleApiError(response)
                    }
                }
            }
        }
    }
}
