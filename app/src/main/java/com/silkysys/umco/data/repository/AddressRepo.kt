package com.silkysys.umco.data.repository

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserDao
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.data.model.address.save.request.Address
import com.silkysys.umco.data.model.address.save.request.Billing
import com.silkysys.umco.data.model.address.save.request.SaveAddressRequest
import com.silkysys.umco.data.model.address.save.request.Shipping
import com.silkysys.umco.data.network.APIService
import com.silkysys.umco.data.network.SafeApiCall
import javax.inject.Inject

class AddressRepo @Inject constructor(
    private val apiService: APIService,
    private val userDao: UserDao
) : SafeApiCall {

    suspend fun createAddress(address: HashMap<String, Any>) = safeApiCall {
        apiService.createAddress(
            true,
            address[Constants.ADDRESS_1].toString(),
            address[Constants.CITY].toString(),
            address[Constants.COUNTRY_NAME].toString(),
            address[Constants.COUNTRY].toString(),
            address[Constants.PHONE].toString(),
            address[Constants.POST_CODE].toString(),
            address[Constants.STATE].toString()
        )
    }

    suspend fun getAllAddresses() = safeApiCall {
        apiService.getAllAddresses(true)
    }

    suspend fun updateAddress(address: HashMap<String, Any>, addressId: Int) = safeApiCall {
        apiService.updateAddress(
            addressId,
            true,
            address[Constants.ADDRESS_1].toString(),
            address[Constants.CITY].toString(),
            address[Constants.COUNTRY_NAME].toString(),
            address[Constants.COUNTRY].toString(),
            address[Constants.PHONE].toString(),
            address[Constants.POST_CODE].toString(),
            address[Constants.STATE].toString()
        )
    }

    suspend fun saveAddress(dataItem: DataItem) = safeApiCall {
        // Get user info from database
        val firstName = userDao.getUser().first_name
        val lastName = userDao.getUser().last_name
        val email = userDao.getUser().email
        val address = Address(dataItem.address1?.get(0)?.let { Address(it) }.toString())
        val addressId = dataItem.id

        // Fill model classes with data to pass it as one object
        val shipping = Shipping(
            address, addressId, lastName.toString(), firstName.toString(),
            email.toString()
        )
        val billing = Billing(
            address, addressId, lastName.toString(), "false",
            firstName.toString(), email.toString()
        )
        apiService.saveAddress(true, SaveAddressRequest(shipping, billing))
    }
}