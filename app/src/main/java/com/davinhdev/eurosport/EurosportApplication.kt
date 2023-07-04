package com.davinhdev.eurosport

import android.app.Application
import com.davinhdev.eurosport.data.di.koinDataModules
import com.davinhdev.eurosport.domain.di.koinDomainModules
import com.davinhdev.eurosport.ui.di.koinAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class EurosportApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@EurosportApplication)
            modules(koinDataModules)
            modules(koinDomainModules)
            modules(koinAppModules)
        }
    }
}
