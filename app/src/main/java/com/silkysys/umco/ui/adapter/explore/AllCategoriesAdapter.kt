package com.silkysys.umco.ui.adapter.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.model.categories.all.DataItem
import com.silkysys.umco.databinding.LayoutSelectCategoryBinding
import com.silkysys.umco.util.OnItemClick

class AllCategoriesAdapter(
    val data: List<DataItem>,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder>() {

    private var selectedId: Int? = null
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoriesViewHolder {
        return AllCategoriesViewHolder(
            LayoutSelectCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            ), onItemClick
        )
    }

    override fun onBindViewHolder(holder: AllCategoriesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class AllCategoriesViewHolder(
        val binding: LayoutSelectCategoryBinding,
        val onItemClick: OnItemClick
    ) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind views
        fun bind(currentItem: DataItem) {
            binding.apply {
                tvCategoryName.text = currentItem.name
                // Swap colors of background and text
                root.context.apply {
                    if (selectedId == currentItem.id) {
                        tvCategoryName.setBackgroundResource(R.drawable.tv_rounded_category)
                        tvCategoryName.setTextColor(
                            ContextCompat.getColor(this, R.color.primaryButton)
                        )
                    } else {
                        tvCategoryName.setBackgroundColor(
                            ContextCompat.getColor(this, R.color.colorWhite)
                        )
                        tvCategoryName.setTextColor(
                            ContextCompat.getColor(this, R.color.category_name)
                        )
                    }
                }
                // Click listener
                itemView.setOnClickListener {
                    if (selectedPosition != -1) notifyItemChanged(selectedPosition)
                    selectedId = currentItem.id
                    selectedPosition = adapterPosition
                    notifyItemChanged(adapterPosition)
                    onItemClick.onItemDetails(currentItem)
                }
            }
        }
    }
}
