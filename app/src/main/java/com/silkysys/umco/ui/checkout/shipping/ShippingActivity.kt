package com.silkysys.umco.ui.checkout.shipping

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.address.save.response.SaveAddressResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityShippingBinding
import com.silkysys.umco.ui.adapter.checkout.ShippingAdapter
import com.silkysys.umco.ui.checkout.payment.PaymentActivity
import com.silkysys.umco.ui.checkout.utils.CheckoutViewModel
import com.silkysys.umco.ui.checkout.utils.OnShippingClick
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import com.silkysys.umco.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShippingActivity : AppCompatActivity(), OnShippingClick, View.OnClickListener {

    private lateinit var binding: ActivityShippingBinding
    private lateinit var viewModel: CheckoutViewModel
    private var selectedShipping: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        updateUi(intent.getParcelableExtra(Constants.SAVE_ADDRESS_RESPONSE))
        binding.btnNext.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        val id = item.id
        // Next button
        if (id == R.id.btn_next) {
            if (selectedShipping != null) saveShippingMethod(selectedShipping)
            else toast(getString(R.string.select_shipping_method))
            // Back button
        } else if (id == R.id.iv_back) onBackPressed()
    }


    override fun onMethodSelected(shippingMethod: String) {
        selectedShipping = shippingMethod
    }


    // Save shipping method via post request when next button is clicked
    private fun saveShippingMethod(selectedShipping: String?) {
        binding.apply {
            progressBar.visibility = VISIBLE
            btnNext.visibility = INVISIBLE
            // Create post request on background thread
            Coroutines.background {
                val response = viewModel.initSaveShipping(selectedShipping)
                // Receive response on main thread
                Coroutines.main {
                    progressBar.visibility = INVISIBLE
                    btnNext.visibility = VISIBLE
                    if (response is Resource.Success) {
                        startActivity(
                            Constants.PLACE_ORDER,
                            response.value.copy(),
                            PaymentActivity::class.java
                        )
                    } else if (response is Resource.Failure) handleApiError(response)
                }
            }
        }
    }


    private fun updateUi(address: SaveAddressResponse?) {
        val shippingMethods = address?.data?.rates
        if (shippingMethods != null) {
            binding.rvShippingMethod.adapter = ShippingAdapter(shippingMethods, this)
        }
    }
}