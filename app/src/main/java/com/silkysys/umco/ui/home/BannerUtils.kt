package com.silkysys.umco.ui.home

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.silkysys.umco.R


class BannerUtils(
    var context: Context,
    private var dots: Array<TextView?>
) {

    companion object {
        const val SOURCE = "&#9679;"
    }

    fun setColors(position: Int) {
        for (i in dots.indices) {
            if (i == position) {
                dots[i]?.setTextColor(ContextCompat.getColor(context, R.color.primaryButton))
            } else {
                dots[i]?.setTextColor(ContextCompat.getColor(context, R.color.banner))
            }
        }
    }

    fun setIndicators(layout: LinearLayout) {
        for (i in dots.indices) {
            dots[i] = TextView(context)
            dots[i]?.text = HtmlCompat.fromHtml(SOURCE, HtmlCompat.FROM_HTML_MODE_LEGACY)
            dots[i]?.textSize = 18f
            layout.addView(dots[i])
        }
    }
}