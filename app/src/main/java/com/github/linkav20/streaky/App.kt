package com.github.linkav20.streaky

import android.app.Application
import com.github.linkav20.network.di.DaggerNetworkComponent
import com.github.linkav20.streaky.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DI.appComponent =
            DaggerAppComponent.builder().appContext(this).build()
        DI.networkComponent = DaggerNetworkComponent.create()
    }
}