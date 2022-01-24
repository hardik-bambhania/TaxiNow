package com.hardik.common.logger

import android.util.Log
import com.hardik.common.BuildConfig

object AppLogger {

    fun debug(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun error(tag: String, exception: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, exception.message, exception)
        }
    }

}