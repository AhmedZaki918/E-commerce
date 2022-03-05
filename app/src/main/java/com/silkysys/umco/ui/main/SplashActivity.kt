package com.silkysys.umco.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.ActivitySplashBinding
import com.silkysys.umco.ui.profile.ProfileViewModel
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.initGetUser()
        viewModel.getUserResponse.observe(this) {
            if (it is Resource.Success) {
                startActivity(MainActivity::class.java)
                finish()
            } else if (it is Resource.Failure) {
                viewModel.deleteUser()
                startActivity(WelcomeActivity::class.java)
                finish()
            }
        }
    }
}