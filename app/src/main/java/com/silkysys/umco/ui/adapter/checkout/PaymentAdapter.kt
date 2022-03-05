package com.silkysys.umco.ui.adapter.checkout

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.data.model.shipping.MethodsItem
import com.silkysys.umco.databinding.LayoutPaymentMethodBinding
import com.silkysys.umco.ui.checkout.utils.OnPaymentClick

class PaymentAdapter(
    private var paymentMethod: List<MethodsItem>,
    private val onPaymentClick: OnPaymentClick
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    // To handle visibility of right sign icon
    private var selectedPayment: String? = null
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        return PaymentViewHolder(
            LayoutPaymentMethodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(paymentMethod[position])
    }

    override fun getItemCount() = paymentMethod.size

    inner class PaymentViewHolder(val binding: LayoutPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: MethodsItem) {
            binding.apply {
                // bind data
                tvPaymentMethod.text = currentItem.method_title

                // Edit visibility of right sign icon
                ivSelectedIcon.apply {
                    visibility = if (selectedPayment.equals(currentItem.method_title)) VISIBLE
                    else INVISIBLE
                }

                // Click listener on item
                itemView.setOnClickListener {
                    // Update right sign icon location on each click
                    if (selectedPosition != -1) notifyItemChanged(selectedPosition)
                    selectedPayment = currentItem.method_title
                    selectedPosition = adapterPosition
                    notifyItemChanged(adapterPosition)
                    // Pass selected payment method to activity
                    onPaymentClick.onItemClicked(currentItem)
                }
            }
        }
    }
}