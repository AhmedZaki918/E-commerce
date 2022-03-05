package com.silkysys.umco.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.databinding.ActivityWelcomeBinding
import com.silkysys.umco.ui.auth.LoginActivity
import com.silkysys.umco.util.startActivity

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.btnGetStarted.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        val id = item.id
        if (id == R.id.btn_login) startActivity(LoginActivity::class.java)
        else if (id == R.id.btn_get_started) startActivity(MainActivity::class.java)
    }
}