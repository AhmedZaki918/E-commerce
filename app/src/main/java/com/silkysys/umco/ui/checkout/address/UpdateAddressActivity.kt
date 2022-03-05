package com.silkysys.umco.ui.checkout.address

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityUpdateAddressBinding
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateAddressActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUpdateAddressBinding
    private lateinit var viewModel: AddressViewModel
    private lateinit var address: HashMap<String, Any>
    private var addressId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        // Display selected address by user
        updateUi(intent.getParcelableExtra(Constants.EDIT_ADDRESS))
        binding.ivBack.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        if (item.id == R.id.iv_back) onBackPressed()
        else if (item.id == R.id.btn_update) {
            if (getUserInput()) updateAddress() else toast(Constants.MISSING_FIELD)
        }
    }


    private fun initViews() {
        address = HashMap()
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
    }


    private fun updateUi(item: DataItem?) {
        if (item != null) {
            addressId = item.id
            binding.apply {
                etAddress.text = setText(item.address1?.get(0))
                etCity.text = setText(item.city)
                etCountry.text = setText(item.country_name)
                etPhoneNumber.text = setText(item.phone)
                etPostCode.text = setText(item.postcode)
                etState.text = setText(item.state)
            }
        }
    }


    private fun getUserInput(): Boolean {
        Constants.apply {
            binding.apply {
                // Get input
                val address1 = etAddress.text.toString().trim()
                val city = etCity.text.toString().trim()
                val country = etCountry.text.toString().trim()
                val phone = etPhoneNumber.text.toString().trim()
                val postcode = etPostCode.text.toString().trim()
                val state = etState.text.toString().trim()
                // Check user input
                return if (address1.isEmpty() || city.isEmpty() || country.isEmpty() ||
                    phone.isEmpty() || postcode.isEmpty() || state.isEmpty()
                ) false
                else {
                    // Pass input to hash map
                    address[ADDRESS_1] = address1
                    address[CITY] = city
                    address[COUNTRY_NAME] = country
                    address[COUNTRY] = country.substring(0, 2)
                    address[PHONE] = phone
                    address[POST_CODE] = postcode
                    address[STATE] = state
                    true
                }
            }
        }
    }


    private fun updateAddress() {
        binding.apply {
            progressBarUpdate.visibility = View.VISIBLE
            btnUpdate.visibility = View.INVISIBLE
            // Run api request on background thread
            Coroutines.background {
                val response = viewModel.initUpdateAddress(address, addressId)
                Coroutines.main {
                    // Receive response on main thread
                    progressBarUpdate.visibility = View.INVISIBLE
                    when (response) {
                        is Resource.Success -> {
                            toast(response.value.message)
                            startActivity(UserAddressActivity::class.java)
                        }
                        is Resource.Failure -> {
                            btnUpdate.visibility = View.VISIBLE
                            handleApiError(response)
                        }
                    }
                }
            }
        }
    }
}