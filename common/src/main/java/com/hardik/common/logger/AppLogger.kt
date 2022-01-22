package com.hardik.common.logger

import android.util.Log

object AppLogger {

    fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun error(tag: String, exception: Throwable) {
        Log.e(tag, exception.message, exception)
    }

}