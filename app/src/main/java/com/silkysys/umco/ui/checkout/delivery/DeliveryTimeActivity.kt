package com.silkysys.umco.ui.checkout.delivery

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.DeliveryDay
import com.silkysys.umco.data.model.DeliveryTime
import com.silkysys.umco.databinding.ActivityDeliveryTimeBinding
import com.silkysys.umco.ui.adapter.checkout.delivery.DeliveryDayAdapter
import com.silkysys.umco.ui.adapter.checkout.delivery.DeliveryTimeAdapter
import com.silkysys.umco.ui.checkout.payment.PaymentActivity
import com.silkysys.umco.util.startActivity

class DeliveryTimeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDeliveryTimeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayDeliveryDay()
        displayDeliveryTime()
        binding.btnNext.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        if (item.id == R.id.btn_create) startActivity(PaymentActivity::class.java)
        else if (item.id == R.id.iv_back) onBackPressed()
    }


    /**
     * Simulate weekdays are coming from api via dummy data,
     * it will replaced soon with real data
     */
    private fun displayDeliveryDay() {
        val deliveryDay = arrayListOf<DeliveryDay>()
        Constants.apply {
            deliveryDay.addAll(
                listOf(
                    DeliveryDay(SATURDAY), DeliveryDay(SUNDAY),
                    DeliveryDay(MONDAY), DeliveryDay(TUESDAY),
                    DeliveryDay(WEDNESDAY), DeliveryDay(THURSDAY), DeliveryDay(FRIDAY),
                )
            )
        }
        // Set adapter to recycler view
        binding.rvWeekdays.adapter = DeliveryDayAdapter(deliveryDay)
    }


    /**
     * Simulate available time coming from api via dummy data,
     * it will replaced soon with real data
     */
    private fun displayDeliveryTime() {
        val deliveryTime = arrayListOf<DeliveryTime>()
        Constants.apply {
            deliveryTime.addAll(
                listOf(
                    DeliveryTime(TEN_AM), DeliveryTime(TWELVE_PM),
                    DeliveryTime(TWO_PM), DeliveryTime(FOUR_PM)
                )
            )
        }
        // Set adapter to recycler view
        binding.rvTime.adapter = DeliveryTimeAdapter(deliveryTime)
    }
}