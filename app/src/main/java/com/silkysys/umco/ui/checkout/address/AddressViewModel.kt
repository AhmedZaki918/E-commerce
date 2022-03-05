package com.silkysys.umco.ui.checkout.address

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.address.all.AllResponse
import com.silkysys.umco.data.model.address.all.DataItem
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.AddressRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repo: AddressRepo) : ViewModel() {

    // Observe the changes on get all addresses by user
    val allAddressResponse = MutableLiveData<Resource<AllResponse>>()

    // Create post request to create an address
    suspend fun initCreateAddress(address: HashMap<String, Any>) = repo.createAddress(address)

    // Create get request to get all addresses saved by user
    fun initGetAllAddress() = viewModelScope.launch {
        allAddressResponse.value = repo.getAllAddresses()
    }

    // Create put request to update the current address
    suspend fun initUpdateAddress(address: HashMap<String, Any>, addressId: Int) =
        repo.updateAddress(address, addressId)

    // Create post request to save an address
    suspend fun initSaveAddress(dataItem: DataItem) = repo.saveAddress(dataItem)
}