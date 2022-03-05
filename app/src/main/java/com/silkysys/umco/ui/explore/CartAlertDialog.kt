package com.silkysys.umco.ui.explore

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.silkysys.umco.databinding.AlertGoCartBinding


// Create alert dialog
inline fun Activity.alertGoToCart(
    func: CartDialogHelper.() -> Unit
): AlertDialog {
    return CartDialogHelper(this).apply {
        func()
    }.create()
}


class CartDialogHelper(val context: Context) {
    // Inflate custom alert dialog layout
    private val binding: AlertGoCartBinding by lazyFastCart {
        AlertGoCartBinding.inflate(LayoutInflater.from(context))
    }

    //  Set view to alert dialog
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(binding.root)

    // initialization
    private var dialog: AlertDialog? = null
    private var cancelable: Boolean = true


    // Click listener on go to cart Button
    fun goToCart(func: (() -> Unit)? = null) {
        with(binding.btnGoToCart) {
            setClickListenerToDialogButton(func)
        }
    }

    // Click listener on Cancel Button
    fun cancelDialog(func: (() -> Unit)? = null) {
        with(binding.ivCancel) {
            setClickListenerToDialogButton(func)
        }
    }

    // Click listener on go shopping Button
    fun continueShopping(func: (() -> Unit)? = null) {
        with(binding.btnGoShopping) {
            setClickListenerToDialogButton(func)
        }
    }

    fun create(): AlertDialog {
        dialog = builder
            .setCancelable(cancelable)
            .create()
        return dialog!!
    }

    private fun View.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}

/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFastCart(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}