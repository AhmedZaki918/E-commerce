package com.silkysys.umco.ui.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.order.create.PlaceOrderResponse
import com.silkysys.umco.data.model.order.get.DataItem
import com.silkysys.umco.databinding.ActivityOrderTrackBinding
import com.silkysys.umco.ui.adapter.checkout.TrackerAdapter
import com.silkysys.umco.ui.checkout.utils.CheckoutViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
This activity updates data from two different model class,
First model class(PlaceOrderResponse) displayed in this activity when the user click on place order in order summary screen.
Second model class(DataItem) displayed when the user open my orders screen and go to the details of a specific order.
 */
@AndroidEntryPoint
class TrackOrderActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOrderTrackBinding
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var ordersUtil: OrdersUtil
    private var placeOrder: PlaceOrderResponse? = null
    private var getOrder: DataItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        updateUi()
        updateDeliveryAddress()
        updateOrderSummary()
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        if (item.id == R.id.iv_back) onBackPressed()
    }


    private fun initViews() {
        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        ordersUtil = OrdersUtil(baseContext)
        // Check intent type first to update ui
        Constants.apply {
            if (intent.getParcelableExtra<PlaceOrderResponse>(PLACE_ORDER) != null) {
                placeOrder = intent.getParcelableExtra(PLACE_ORDER)
            } else{
                getOrder = intent.getParcelableExtra(GET_ORDER)
            }
        }
    }


    private fun updateUi() {
        binding.apply {
            if (placeOrder != null) {
                placeOrder?.order.apply {
                    updateOrderIdAndDate(this?.id, this?.created_at)
                    tvMobileNumber.text = this?.shipping_address?.phone
                    tvTrackerState.text = this?.status_label
                    rvItems.adapter = this?.items?.let { TrackerAdapter(it, null) }
                }
            } else {
                getOrder.apply {
                    updateOrderIdAndDate(this?.id, this?.created_at)
                    tvMobileNumber.text = this?.shipping_address?.phone
                    tvTrackerState.text = this?.status_label
                    rvItems.adapter = TrackerAdapter(null, this?.items)
                }
            }
        }
    }


    private fun updateDeliveryAddress() {
        binding.apply {
            if (placeOrder != null) {
                tvCustomerName.text = placeOrder?.order?.customer_first_name
                tvAddress.text = ordersUtil.formatDeliveryAddress(
                    placeOrder?.order?.shipping_address?.address1?.get(0).toString(),
                    placeOrder?.order
                )
            } else {
                tvCustomerName.text = getOrder?.customer_first_name
                tvAddress.text = ordersUtil.formatDeliveryAddress(
                    getOrder?.shipping_address?.address1?.get(0).toString(),
                    getOrder
                )
            }
        }
    }


    private fun updateOrderIdAndDate(id: Int?, orderDate: String?) {
        binding.apply {
            val orderId = getString(R.string.order_id) + id
            tvOrderId.text = orderId
            // Order date
            val formattedDate =
                getString(R.string.placed_on) + " " + orderDate?.indexOf("T")
                    ?.let { orderDate.substring(0, it) }
            tvPlacedOrder.text = formattedDate
        }
    }


    private fun updateOrderSummary() {
        binding.apply {
            if (placeOrder != null) {
                placeOrder?.order.apply {
                    tvPaymentMethod.text = this?.payment_title
                    tvSubtotal.text = this?.formated_base_sub_total
                    tvDelivery.text = this?.formated_base_shipping_amount
                    tvTax.text = this?.formated_base_tax_amount
                    tvTotal.text = this?.formated_base_grand_total
                }
            } else {
                getOrder.apply {
                    tvPaymentMethod.text = this?.payment_title
                    tvSubtotal.text = this?.formated_base_sub_total
                    tvDelivery.text = this?.formated_base_shipping_amount
                    tvTax.text = this?.formated_base_tax_amount
                    tvTotal.text = this?.formated_base_grand_total
                }
            }
        }
    }
}