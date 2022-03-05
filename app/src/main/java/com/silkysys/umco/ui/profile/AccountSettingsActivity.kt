package com.silkysys.umco.ui.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silkysys.umco.R
import com.silkysys.umco.databinding.ActivityAccountSettingsBinding

class AccountSettingsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAccountSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener(this)
    }

    override fun onClick(item: View) {
        val id = item.id
        if (id == R.id.iv_back) onBackPressed()
    }
}