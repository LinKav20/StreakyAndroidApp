package com.github.linkav20.streaky.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.streaky.DI
import com.github.linkav20.streaky.di.ScreenScope
import com.github.linkav20.streaky.di.ViewModelFactory
import com.github.linkav20.streaky.di.ViewModelKey
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [LoginModule::class])
@ScreenScope
interface LoginComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): LoginComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerLoginComponent.builder().appContext(DI.appComponent.appContext()).build()
        }
    }
}

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun productViewModel(viewModel: LoginViewModel): ViewModel
}