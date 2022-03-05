package com.silkysys.umco.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.silkysys.umco.R
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.databinding.ActivitySearchBinding
import com.silkysys.umco.databinding.LayoutBottomSheetBinding
import com.silkysys.umco.ui.main.MainActivity
import com.silkysys.umco.util.startActivity

class SearchActivity : AppCompatActivity(), View.OnClickListener,
    SearchView.OnQueryTextListener {


    private lateinit var binding: ActivitySearchBinding
    private lateinit var bindingDialogLayout: LayoutBottomSheetBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setClickListener()
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.iv_back -> onBackPressed()
            R.id.iv_filter -> setupBottomSheetDialog()
            R.id.iv_cancel, R.id.btn_apply -> bottomSheetDialog.cancel()
            R.id.btn_back_home -> startActivity(MainActivity::class.java)
            R.id.btn_clear -> {}
            R.id.btn_try_again -> startActivity(
                Constants.OPEN_FRAGMENT,
                Constants.EXPLORE,
                MainActivity::class.java
            )
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


    private fun setClickListener() {
        binding.apply {
            this@SearchActivity.apply {
                ivBack.setOnClickListener(this)
                ivFilter.setOnClickListener(this)
                btnTryAgain.setOnClickListener(this)
                btnBackHome.setOnClickListener(this)
            }
        }
        bindingDialogLayout.apply {
            this@SearchActivity.apply {
                ivCancel.setOnClickListener(this)
                btnApply.setOnClickListener(this)
                btnClear.setOnClickListener(this)
            }
        }
    }


    private fun initViews() {
        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        bindingDialogLayout = LayoutBottomSheetBinding.inflate(layoutInflater)
    }


    private fun setupBottomSheetDialog() {
        bottomSheetDialog.apply {
            setContentView(bindingDialogLayout.root)
            show()
        }
    }
}