package com.hardik.taxinow

import android.app.Application
import com.hardik.common.logger.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaxiNowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.debug(
            TaxiNowApplication::class.java.name,
            "TaxiNowApplication Application launch"
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        AppLogger.debug(
            TaxiNowApplication::class.java.name,
            "TaxiNowApplication Application close"
        )
    }
}