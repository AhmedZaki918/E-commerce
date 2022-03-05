package com.silkysys.umco.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.text.Editable
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.network.Resource
import com.squareup.picasso.Picasso


fun <T> Context.startActivity(cls: Class<T>) {
    Intent(this, cls).apply {
        startActivity(this)
    }
}


fun <T> Context.startActivity(key: String, value: Any?, cls: Class<T>) {
    Intent(this, cls).apply {
        when (value) {
            is String -> putExtra(key, value)
            is Int -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
        }
        startActivity(this)
    }
}

fun Context.openUrl(url: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        startActivity(this)
    }
}


fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun setText(text: String?): Editable? {
    return Editable.Factory.getInstance().newEditable(text)
}


fun setupPicasso(url: String?, view: ImageView) {
    Picasso.get()
        .load(url)
        .into(view)
}


fun Activity.handleApiError(
    failure: Resource.Failure
) {
    when {
        failure.isNetworkError -> toast(Constants.CHECK_CONNECTION)
        failure.errorCode == 401 -> {
            toast(failure.errorBody?.string().toString())
        }
        else -> toast(failure.errorBody?.string().toString())
    }
}


fun Fragment.handleApiError(
    failure: Resource.Failure
) {
    when {
        failure.isNetworkError -> requireContext().toast(Constants.CHECK_CONNECTION)
        failure.errorCode == 401 -> {
            requireContext().toast(failure.errorBody?.string().toString())
        }
        else -> requireContext().toast(failure.errorBody?.string().toString())
    }
}