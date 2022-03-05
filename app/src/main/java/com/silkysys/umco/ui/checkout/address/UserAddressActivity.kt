package com.silkysys.umco.ui.checkout.address

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.address.all.AllResponse
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityUserAddressBinding
import com.silkysys.umco.ui.adapter.checkout.AddressAdapter
import com.silkysys.umco.ui.checkout.shipping.ShippingActivity
import com.silkysys.umco.ui.checkout.utils.OnAddressClick
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAddressActivity : AppCompatActivity(), View.OnClickListener, OnAddressClick {

    private lateinit var binding: ActivityUserAddressBinding
    private lateinit var viewModel: AddressViewModel
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        viewModel.initGetAllAddress()
        viewModel.allAddressResponse.observe(this) { response ->
            updateUi(response)
        }
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.btn_create_address -> startActivity(CreateAddressActivity::class.java)
            R.id.iv_back -> onBackPressed()
        }
    }


    override fun onEditAddress(dataItem: DataItem) {
        startActivity(
            Constants.EDIT_ADDRESS,
            dataItem,
            UpdateAddressActivity::class.java
        )
    }


    override fun onAddressSelected(dataItem: DataItem) {
        binding.progressBarAddress.visibility = VISIBLE
        Coroutines.background {
            val response = viewModel.initSaveAddress(dataItem)
            Coroutines.main {
                binding.progressBarAddress.visibility = INVISIBLE
                if (response is Resource.Success) {
                    startActivity(
                        Constants.SAVE_ADDRESS_RESPONSE,
                        response.value.copy(),
                        ShippingActivity::class.java
                    )

                } else if (response is Resource.Failure) handleApiError(response)
            }
        }
    }


    private fun setClickListener() {
        binding.apply {
            this@UserAddressActivity.apply {
                btnCreateAddress.setOnClickListener(this)
                ivBack.setOnClickListener(this)
            }
        }
    }


    private fun updateUi(response: Resource<AllResponse>?) {
        binding.apply {
            progressBarAddress.visibility = INVISIBLE
            if (response is Resource.Success) {
                if (response.value.data != null) {
                    addressAdapter = AddressAdapter(
                        response.value.data,
                        this@UserAddressActivity
                    )
                    rvAddress.adapter = addressAdapter
                    // Display empty state if adapter is empty
                    if (addressAdapter.itemCount == 0) {
                        ivNoAddress.visibility = VISIBLE
                        tvNoAddress.visibility = VISIBLE
                    }
                }
            } else if (response is Resource.Failure) handleApiError(response)
        }
    }
}