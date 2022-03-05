package com.silkysys.umco.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserDao
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.model.auth.login.LoginResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.data.repository.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepo,
    private var userPreferences: UserPreferences,
    private var userDao: UserDao
) : ViewModel() {

    // Mutable Live data to observe on it
    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    // Send post request to the server
    fun initLogin(email: String, password: String) = viewModelScope.launch {
        loginResponse.value = repo.login(email, password)
    }

    fun saveUserInfo(response: Resource.Success<LoginResponse>) {
        // Save user token
        userPreferences.write(Constants.USER_TOKEN, response.value.token)
        // Save user info
        viewModelScope.launch { userDao.addUser(response.value.data) }
    }
}