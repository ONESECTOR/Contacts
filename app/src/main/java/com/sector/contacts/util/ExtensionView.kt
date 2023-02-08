package com.sector.contacts.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.postDelayed
import androidx.core.view.updatePadding
import com.google.android.material.internal.ViewUtils

@SuppressLint("RestrictedApi")
fun View.addSystemTopPadding(
    targetView: View = this
) {
    ViewUtils.doOnApplyWindowInsets(
        targetView
    ) { _, insets, initialPadding ->
        targetView.updatePadding(
            top = initialPadding.top + insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        )
        insets
    }
}

@SuppressLint("RestrictedApi")
fun View.addSystemBottomPadding(
    targetView: View = this
) {
    ViewUtils.doOnApplyWindowInsets(
        targetView
    ) { _, insets, initialPadding ->
        targetView.updatePadding(
            bottom = initialPadding.bottom + insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()).bottom
        )
        insets
    }
}

fun View.showSoftKeyboard(view: View = this, delayInMillis: Long = 200L) {
    if (view.requestFocus()) {
        view.postDelayed(delayInMillis) {
            val inputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}

fun View.hideSoftKeyboard(view: View = this, requestParentFocus: Boolean = false) {
    val inputMethodManager =
        view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
    when (requestParentFocus) {
        true -> {
            (view.parent as View).requestFocus()
        }
        else -> {}
    }
}