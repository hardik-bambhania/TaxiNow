package com.hardik.taxinow.utils

import android.view.View

/**
 * This class is responsible to manage all extension functions
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Double.toStringWithFourPrecision(): String {
    return String.format("%.4f", this)
}