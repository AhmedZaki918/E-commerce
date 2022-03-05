package com.silkysys.umco.ui.checkout.address

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityCreateAddressBinding
import com.silkysys.umco.util.Coroutines
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import com.silkysys.umco.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateAddressActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCreateAddressBinding
    private lateinit var viewModel: AddressViewModel
    private lateinit var address: HashMap<String, Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        address = HashMap()
        binding.btnCreate.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }

    override fun onClick(item: View) {
        if (item.id == R.id.btn_create) {
            if (getUserInput()) createAddress() else toast(Constants.MISSING_FIELD)
        } else onBackPressed()
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


    private fun createAddress() {
        binding.apply {
            progressBarShipping.visibility = VISIBLE
            btnCreate.visibility = INVISIBLE
            // Run api request on background thread
            Coroutines.background {
                val response = viewModel.initCreateAddress(address)
                Coroutines.main {
                    // Receive response on main thread
                    progressBarShipping.visibility = INVISIBLE
                    when (response) {
                        is Resource.Success -> {
                            toast(response.value.message)
                            startActivity(UserAddressActivity::class.java)
                        }
                        is Resource.Failure -> {
                            btnCreate.visibility = VISIBLE
                            handleApiError(response)
                        }
                    }
                }
            }
        }
    }
}