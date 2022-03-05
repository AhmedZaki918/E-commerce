package com.silkysys.umco.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    @Inject
    lateinit var userPreferences: UserPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Open cart fragment
        if (intent.extras != null) {
            val value = intent.extras!!.getString(Constants.OPEN_FRAGMENT)
            if (value == Constants.CART) openFragment(R.id.action_global_cart_nav)
            else openFragment(R.id.action_global_explore_nav)
            // Setup bottom navigation
        } else setupBottomNavigation()
    }

    private fun openFragment(actionId: Int) {
        setupBottomNavigation()
        navHostFragment.navController.apply {
            popBackStack()
            navigate(actionId)
        }
    }

    private fun setupBottomNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}