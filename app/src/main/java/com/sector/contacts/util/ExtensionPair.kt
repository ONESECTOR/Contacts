package com.sector.contacts.util

import android.content.Context
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

fun Pair<TextInputEditText, MaterialTextView>.setActiveField(
    context: Context,
    active: Boolean = true
) {
    when (active) {
        true -> {
            first.setPadding(
                (16).dpToPx(context.resources.displayMetrics.density),
                (15).dpToPx(context.resources.displayMetrics.density),
                0,
                0
            )
            first.hint = ""
            second.isVisible = true
        }
        false -> {
            if (first.text?.isEmpty() == true) {
                first.setPadding(
                    (16).dpToPx(context.resources.displayMetrics.density),
                    0,
                    0,
                    0
                )
                second.isGone = true
            }
            first.hint = second.text
        }
    }
}