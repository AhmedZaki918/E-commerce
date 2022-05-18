package com.silkysys.umco.ui.auth

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivityLoginBinding
import com.silkysys.umco.ui.main.MainActivity
import com.silkysys.umco.util.AuthViewModel
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.openUrl
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private var currentStatus = false
    private lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        // Observe the changes on login response via live data
        viewModel.loginResponse.observe(this) { response ->
            binding.btnLogin.isClickable = true
            when (response) {
                is Resource.Success -> {
                    viewModel.saveUserInfo(response)
                    startActivity(MainActivity::class.java)
                    finish()
                }
                is Resource.Failure -> {
                    handleApiError(response)
                    binding.progressBar.visibility = INVISIBLE
                }
            }
        }
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.btn_rest_password -> startActivity(ResetPasswordActivity::class.java)
            R.id.iv_back -> onBackPressed()
            R.id.ib_showPassword -> showOrHidePassword(currentStatus)
            R.id.btn_skip -> startActivity(MainActivity::class.java)
            R.id.btn_login -> login()
            R.id.btn_create_account -> openUrl(Constants.NEW_ACCOUNT)
        }
    }


    // Create login request from api
    private fun login() {
        binding.apply {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            // Check if email or password is empty, else login
            when {
                email.isEmpty() -> etEmail.error = Constants.REQUIRED_FIELD
                password.isEmpty() -> etPassword.error = Constants.REQUIRED_FIELD
                // Invalid email
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = Constants.INVALID_EMAIL
                }
                else -> {
                    // Login
                    btnLogin.isClickable = false
                    progressBar.visibility = VISIBLE
                    viewModel.initLogin(email, password)
                }
            }
        }
    }


    private fun setClickListeners() {
        binding.apply {
            this@LoginActivity.apply {
                ivBack.setOnClickListener(this)
                btnRestPassword.setOnClickListener(this)
                ibShowPassword.setOnClickListener(this)
                btnSkip.setOnClickListener(this)
                btnLogin.setOnClickListener(this)
                btnCreateAccount.setOnClickListener(this)
            }
        }
    }


    private fun showOrHidePassword(status: Boolean) {
        binding.etPassword.apply {
            if (status) {
                transformationMethod = PasswordTransformationMethod()
                currentStatus = false
            } else {
                transformationMethod = null
                currentStatus = true
            }
        }
    }
}