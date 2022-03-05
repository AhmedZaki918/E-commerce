package com.silkysys.umco.ui.checkout.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.silkysys.umco.databinding.PaymentAlertDialogBinding


// Create alert dialog
inline fun Activity.alert(
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this).apply {
        func()
    }.create()
}


class AlertDialogHelper(val context: Context) {
    // Inflate custom alert dialog layout
    private val binding: PaymentAlertDialogBinding by lazyFast {
        PaymentAlertDialogBinding.inflate(LayoutInflater.from(context))
    }

    //  Set view to alert dialog
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(binding.root)

    // initialization
    private var dialog: AlertDialog? = null
    private var cancelable: Boolean = true


    // Click listener on Pay Button
    fun payButton(func: (() -> Unit)? = null) {
        with(binding.btnPay) {
            setClickListenerToDialogButton(func)
        }
    }

    // Click listener on Cancel Button
    fun cancelButton(func: (() -> Unit)? = null) {
        with(binding.ivCancel) {
            setClickListenerToDialogButton(func)
        }
    }

    // Get credit card info from edit text views
    fun getData(): List<String> {
        val creditCardInfo = arrayListOf<String>()
        binding.apply {
            creditCardInfo.addAll(
                listOf(
                    etCardNumber.text.toString(),
                    etExpireDate.text.toString(),
                    etCvv.text.toString(),
                    etFullName.text.toString()
                )
            )
        }
        return creditCardInfo
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
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}