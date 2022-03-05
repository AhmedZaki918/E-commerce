package com.silkysys.umco.ui.order


import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.model.order.get.AllOrdersResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityMyOrdersBinding
import com.silkysys.umco.ui.adapter.order.OrdersAdapter
import com.silkysys.umco.ui.checkout.utils.CheckoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOrdersActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: CheckoutViewModel
    private lateinit var binding: ActivityMyOrdersBinding
    private var orderResponse: Resource<AllOrdersResponse>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        viewModel.ordersResponse.observe(this) { response ->
            orderResponse = response
            updateOnGoing(response)
        }
        viewModel.initGetOrders()
        setClickListener()
    }


    override fun onClick(item: View) {
        binding.apply {
            when (item.id) {
                R.id.iv_back -> onBackPressed()
                R.id.btn_ongoing -> orderResponse?.let { updateOnGoing(it) }
                R.id.btn_history -> {
                    swapColors(btnHistory)
                    rvOrders.visibility = INVISIBLE
                    ivHistoryZero.visibility = VISIBLE
                    tvNoOrders.visibility = VISIBLE
                }
            }
        }
    }


    private fun setClickListener() {
        binding.apply {
            this@MyOrdersActivity.apply {
                ivBack.setOnClickListener(this)
                btnOngoing.setOnClickListener(this)
                btnHistory.setOnClickListener(this)
            }
        }
    }


    private fun updateOnGoing(response: Resource<AllOrdersResponse>) {
        binding.apply {
            if (!rvOrders.isVisible) rvOrders.visibility = VISIBLE
            swapColors(btnOngoing)
            progressBar.visibility = INVISIBLE

            if (response is Resource.Success) {
                val ordersAdapter = response.value.data?.let { OrdersAdapter(it) }
                if (ordersAdapter?.itemCount == 0) displayEmptyState()
                else {
                    // Check first if empty state views are visible, set it invisible
                    if (tvNoOrders.isVisible) tvNoOrders.visibility = INVISIBLE
                    if (ivHistoryZero.isVisible) ivHistoryZero.visibility = INVISIBLE
                    rvOrders.adapter = ordersAdapter
                }
            } else if (response is Resource.Failure) displayEmptyState()
        }
    }


    // Swap colors on history button, ongoing button
    private fun swapColors(buttonType: Button) {
        val orderUtil = OrdersUtil(baseContext)
        binding.apply {
            if (buttonType == btnHistory) orderUtil.editStyle(btnHistory, btnOngoing)
            else orderUtil.editStyle(btnOngoing, btnHistory)
        }
    }


    private fun displayEmptyState() {
        binding.apply {
            if (ivHistoryZero.isVisible) ivHistoryZero.visibility = INVISIBLE
            rvOrders.visibility = INVISIBLE
            ivOngoingZero.visibility = VISIBLE
            tvNoOrders.visibility = VISIBLE
        }
    }
}