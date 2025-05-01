package com.benzo.benzomobile.app

import android.app.Application
import com.benzo.benzomobile.di.dataModule
import com.benzo.benzomobile.di.domainModule
import com.benzo.benzomobile.di.navModule
import com.benzo.benzomobile.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule,
                    navModule
                )
            )
        }
    }
}