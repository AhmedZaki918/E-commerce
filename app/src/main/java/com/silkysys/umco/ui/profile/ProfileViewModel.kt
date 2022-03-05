package com.silkysys.umco.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.model.auth.login.LoginResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepo,
) : ViewModel() {

    val getUserResponse = MutableLiveData<Resource<LoginResponse>>()

    // Create get request to get user info
    fun initGetUser() = viewModelScope.launch { getUserResponse.value = repo.getUser() }

    // Create get request to logout user
    suspend fun logout() = repo.logout()

    // Delete all user info from local database (Logout only case)
    fun deleteUser() {
        repo.deleteUserInfo()
    }
}