package com.silkysys.umco.ui.cart

import android.view.View
import android.view.View.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.silkysys.umco.R
import com.silkysys.umco.data.model.cart.Data
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.FragmentCartBinding
import com.silkysys.umco.databinding.LayoutCartBinding
import com.silkysys.umco.ui.adapter.cart.CartAdapter
import com.silkysys.umco.ui.checkout.address.UserAddressActivity
import com.silkysys.umco.ui.search.SearchActivity
import com.silkysys.umco.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate),
    OnClickListener, OnCartDetails {

    private lateinit var viewModel: CartViewModel

    override fun FragmentCartBinding.initialize() {
        setClickListeners()
        viewModel = ViewModelProvider(this@CartFragment)[CartViewModel::class.java]
        viewModel.cartDetailsResponse.observe(viewLifecycleOwner) { response ->
            binding.progressBarCart.visibility = GONE
            if (response is Resource.Success) updateCart(response.value.data)
            else if (response is Resource.Failure) handleApiError(response)
        }
        viewModel.initCartDetails()
    }


    override fun onClick(item: View) {
        when (item.id) {
            R.id.btn_back_home, R.id.iv_back -> requireActivity().onBackPressed()
            R.id.iv_search -> requireContext().startActivity(SearchActivity::class.java)
            R.id.btn_try_again -> {
                findNavController().apply {
                    popBackStack()
                    navigate(R.id.action_global_explore_nav)
                }
            }
            R.id.btn_checkout -> requireContext().startActivity(UserAddressActivity::class.java)
        }
    }


    override fun removeItem(id: Int) {
        Coroutines.background {
            val response = viewModel.initRemoveItem(id)
            withContext(Dispatchers.Main) {
                when (response) {
                    is Resource.Success -> {
                        requireContext().toast(response.value.message)
                        updateCart(response.value.data)
                    }
                    is Resource.Failure -> handleApiError(response)
                }
            }
        }
    }


    override fun updateItem(id: Int, operation: Int, binding: LayoutCartBinding) {
        binding.apply {
            progressBar.visibility = VISIBLE
            tvQuantity.visibility = INVISIBLE
            // Update item on background thread
            Coroutines.background {
                val response = viewModel.initUpdateItem(id, operation)
                Coroutines.main {
                    when (response) {
                        is Resource.Success -> {
                            progressBar.visibility = INVISIBLE
                            tvQuantity.visibility = VISIBLE
                            // Update cart only when response is not empty
                            response.value.data.apply {
                                if (this != null) updateCart(this)
                                else requireContext().toast(getString(R.string.error_update_cart))
                            }
                        }
                        is Resource.Failure -> handleApiError(response)
                    }
                }
            }
        }
    }


    private fun updateCart(data: Data?) {
        if (data != null) {
            binding.apply {
                rvCart.adapter = CartAdapter(data.items, this@CartFragment)
                tvSubtotal.text = data.formated_sub_total
                tvDiscountPrice.text = data.formated_discount
                tvTotalTax.text = data.formated_tax_total
                tvGrandTotal.text = data.formated_base_grand_total
                viewModel.isEmptyCart(false, binding)
            }
        }
        // Display empty cart list
        else viewModel.isEmptyCart(true, binding)
    }


    private fun setClickListeners() {
        binding.apply {
            this@CartFragment.apply {
                btnTryAgain.setOnClickListener(this)
                btnBackHome.setOnClickListener(this)
                ivBack.setOnClickListener(this)
                ivSearch.setOnClickListener(this)
                btnCheckout.setOnClickListener(this)
            }
        }
    }
}