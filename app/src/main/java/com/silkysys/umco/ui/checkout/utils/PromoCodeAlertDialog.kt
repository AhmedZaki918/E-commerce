package com.silkysys.umco.ui.checkout.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.silkysys.umco.databinding.PromoCodeAlertDialogBinding


// Create alert dialog
inline fun Activity.alertPromoCode(
    func: PromoCodeDialogHelper.() -> Unit
): AlertDialog {
    return PromoCodeDialogHelper(this).apply {
        func()
    }.create()
}


class PromoCodeDialogHelper(val context: Context) {
    // Inflate custom alert dialog layout
    private val binding: PromoCodeAlertDialogBinding by lazyFastPromoCode {
        PromoCodeAlertDialogBinding.inflate(LayoutInflater.from(context))
    }

    //  Set view to alert dialog
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(binding.root)

    // initialization
    private var dialog: AlertDialog? = null
    private var cancelable: Boolean = true


    // Click listener on Pay Button
    fun confirmButton(func: (() -> Unit)? = null) {
        with(binding.btnConfirm) {
            setClickListenerToDialogButton(func)
        }
    }

    // Click listener on Cancel Button
    fun cancelButton(func: (() -> Unit)? = null) {
        with(binding.ivCancel) {
            setClickListenerToDialogButton(func)
        }
    }


    fun getPromoCode() =
        binding.etPromoCode.text.toString().trim()


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
fun <T> lazyFastPromoCode(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}