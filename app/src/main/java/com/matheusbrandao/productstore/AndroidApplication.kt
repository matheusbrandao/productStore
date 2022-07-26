package com.matheusbrandao.productstore

import android.app.Application
import com.matheusbrandao.productstore.di.dataSourceModule
import com.matheusbrandao.productstore.di.presentationModule
import com.matheusbrandao.productstore.di.repositoryModule
import com.matheusbrandao.productstore.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(
                listOf(
                    presentationModule,
                    serviceModule,
                    dataSourceModule,
                    repositoryModule
                )
            )
        }
    }
}
