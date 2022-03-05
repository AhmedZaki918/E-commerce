package com.silkysys.umco.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.databinding.ActivityResetPassswordBinding

class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityResetPassswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPassswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener(this)
    }

    override fun onClick(item: View) {
        val id = item.id
        if (id == R.id.iv_back) onBackPressed()
    }
}