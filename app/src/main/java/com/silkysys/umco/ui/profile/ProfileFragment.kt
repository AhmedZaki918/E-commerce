package com.silkysys.umco.ui.profile

import android.view.View
import android.view.View.*
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.model.auth.login.LoginResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.FragmentProfileBinding
import com.silkysys.umco.ui.main.WelcomeActivity
import com.silkysys.umco.ui.order.MyOrdersActivity
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate),
    OnClickListener {

    private lateinit var viewModel: ProfileViewModel

    override fun FragmentProfileBinding.initialize() {
        setClickListener()
        viewModel = ViewModelProvider(this@ProfileFragment)[ProfileViewModel::class.java]
        viewModel.getUserResponse.observe(viewLifecycleOwner) { response ->
            updateUi(response)
        }
        viewModel.initGetUser()
    }


    override fun onClick(item: View) {
        requireContext().apply {
            when (item.id) {
                R.id.cv_my_orders -> startActivity(MyOrdersActivity::class.java)
                R.id.cv_account_settings -> startActivity(AccountSettingsActivity::class.java)
                R.id.iv_back -> requireActivity().onBackPressed()
                R.id.cv_auth -> {
                    // Check text on TextView if it's login, login else logout
                    if (binding.tvAuth.text == getString(R.string.login)) {
                        startActivity(WelcomeActivity::class.java)
                    } else logOut()
                }
            }
        }
    }


    private fun updateUi(response: Resource<LoginResponse>) {
        binding.apply {
            progressBar.visibility = INVISIBLE
            tvProfileName.visibility = VISIBLE
            tvEmail.visibility = VISIBLE
            if (response is Resource.Success) {
                response.value.data.apply {
                    tvProfileName.text = first_name
                    tvEmail.text = email
                }
            } else if (response is Resource.Failure) tvAuth.text = getString(R.string.login)
        }
    }


    private fun setClickListener() {
        binding.apply {
            this@ProfileFragment.apply {
                cvMyOrders.setOnClickListener(this)
                cvAccountSettings.setOnClickListener(this)
                ivBack.setOnClickListener(this)
                cvAuth.setOnClickListener(this)
            }
        }
    }


    private fun logOut() {
        Coroutines.background {
            val response = viewModel.logout()
            Coroutines.main {
                if (response is Resource.Success) {
                    requireContext().toast(response.value.message)
                    viewModel.deleteUser()
                    requireContext().startActivity(WelcomeActivity::class.java)
                } else if (response is Resource.Failure) handleApiError(response)
            }
        }
    }
}