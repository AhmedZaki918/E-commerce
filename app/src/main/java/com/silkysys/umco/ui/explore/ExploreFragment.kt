package com.silkysys.umco.ui.explore

import android.view.View
import android.view.View.INVISIBLE
import androidx.lifecycle.ViewModelProvider
import com.silkysys.umco.R
import com.silkysys.umco.data.model.categories.all.AllCategoriesResponse
import com.silkysys.umco.data.network.Resource
import com.silkysys.umco.databinding.FragmentExploreBinding
import com.silkysys.umco.ui.adapter.explore.ExploreAdapter
import com.silkysys.umco.ui.search.SearchActivity
import com.silkysys.umco.util.BaseFragment
import com.silkysys.umco.util.handleApiError
import com.silkysys.umco.util.startActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate),
    View.OnClickListener {
    private lateinit var viewModel: ExploreViewModel

    override fun FragmentExploreBinding.initialize() {
        viewModel = ViewModelProvider(this@ExploreFragment)[ExploreViewModel::class.java]
        viewModel.initGetCategories()
        viewModel.categoriesResponse.observe(viewLifecycleOwner) { response ->
            updateUi(response)
        }
        binding.ivSearch.setOnClickListener(this@ExploreFragment)
    }

    override fun onClick(item: View) {
        if (item.id == R.id.iv_search) requireContext().startActivity(SearchActivity::class.java)
    }

    private fun updateUi(response: Resource<AllCategoriesResponse>?) {
        binding.progressBar.visibility = INVISIBLE
        if (response is Resource.Success) {
            val categories = response.value.data
            if (categories != null) {
                // Based on title only(true or false) we display required layout
                binding.rvCategory.adapter = ExploreAdapter(categories)
            }
        } else if (response is Resource.Failure) handleApiError(response)
    }
}