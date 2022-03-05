package com.silkysys.umco.ui.checkout.summary

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.order.create.PlaceOrderResponse
import com.silkysys.umco.databinding.ActivityCheckoutBinding
import com.silkysys.umco.ui.main.MainActivity
import com.silkysys.umco.ui.order.TrackOrderActivity
import com.silkysys.umco.util.startActivity

class CheckoutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCheckoutBinding
    private var order: PlaceOrderResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        order = intent.getParcelableExtra(Constants.PLACE_ORDER)
        binding.btnTrackOrder.setOnClickListener(this)
        binding.btnBackHome.setOnClickListener(this)
    }

    override fun onClick(item: View) {
        val id = item.id
        if (id == R.id.btn_track_order) {
            startActivity(
                Constants.PLACE_ORDER,
                order,
                TrackOrderActivity::class.java
            )
        } else if (id == R.id.btn_back_home) startActivity(MainActivity::class.java)
    }
}