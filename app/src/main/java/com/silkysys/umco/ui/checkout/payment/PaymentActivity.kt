package com.silkysys.umco.ui.checkout.payment

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.shipping.MethodsItem
import com.silkysys.umco.data.model.shipping.ShippingResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityPaymentBinding
import com.silkysys.umco.ui.adapter.checkout.PaymentAdapter
import com.silkysys.umco.ui.checkout.summary.OrderSummaryActivity
import com.silkysys.umco.ui.checkout.utils.CheckoutViewModel
import com.silkysys.umco.ui.checkout.utils.OnPaymentClick
import com.silkysys.umco.ui.checkout.utils.alert
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import com.silkysys.umco.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity(), View.OnClickListener, OnPaymentClick {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var viewModel: CheckoutViewModel
    private var paymentMethod: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        updateUi(intent.getParcelableExtra(Constants.PLACE_ORDER))
        binding.btnNext.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        Constants.apply {
            //  Next button
            if (item.id == R.id.btn_next) {
                if (paymentMethod != null) {
                    if (paymentMethod == PAYPAL || paymentMethod == PAYPAL_STANDARD) getCreditCardInfo()
                    else savePaymentMethod(paymentMethod)
                } else toast(getString(R.string.select_payment))
                // Back button
            } else if (item.id == R.id.iv_back) onBackPressed()
        }
    }


    override fun onItemClicked(methodItem: MethodsItem?) {
        paymentMethod = methodItem?.method
    }


    private fun savePaymentMethod(paymentMethod: String?) {
        binding.apply {
            btnNext.visibility = INVISIBLE
            progressBar.visibility = VISIBLE

            Coroutines.background {
                val response = viewModel.initSavePayment(paymentMethod)
                Coroutines.main {
                    btnNext.visibility = VISIBLE
                    progressBar.visibility = INVISIBLE
                    if (response is Resource.Success) {
                        startActivity(
                            Constants.PLACE_ORDER,
                            response.value.copy(),
                            OrderSummaryActivity::class.java
                        )
                    } else if (response is Resource.Failure) handleApiError(response)
                }
            }
        }
    }


    private fun updateUi(response: ShippingResponse?) {
        // Set adapter to recycler view
        if (response?.data?.methods != null) {
            binding.rvPaymentMethod.adapter =
                PaymentAdapter(response.data.methods, this)
        }
    }


    // Open alert dialog to get credit card info
    private fun getCreditCardInfo() {
        alert {
            payButton {
                val cardNumber = getData()[0]
                val expireDate = getData()[1]
                val cvv = getData()[2]
                val fullName = getData()[3]
                // This toast line for "TEST ONLY"
                toast(" Demo \n $cardNumber \n $expireDate \n $cvv \n $fullName")
            }
            cancelButton()
        }.show()
    }
}