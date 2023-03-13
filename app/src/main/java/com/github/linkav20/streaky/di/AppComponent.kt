package com.github.linkav20.streaky.di

import android.content.Context
import dagger.*
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    //fun internetConnectivityObserver(): InternetNetworkInformant

    fun appContext(): Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}

@Module
abstract class AppModule {
    //@Binds
    //@Singleton
    //abstract fun internetConnectivity(manager: NetworkConnectivityObserver): InternetNetworkInformant
}


