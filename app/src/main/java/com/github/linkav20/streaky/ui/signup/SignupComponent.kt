package com.github.linkav20.streaky.ui.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.DI
import com.github.linkav20.streaky.di.ScreenScope
import com.github.linkav20.streaky.di.ViewModelFactory
import com.github.linkav20.streaky.di.ViewModelKey
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [SignupModule::class])
@ScreenScope
interface SignupComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): SignupComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerSignupComponent.builder().appContext(DI.appComponent.appContext())
                .api(DI.networkComponent.api()).build()
        }
    }
}

@Module
abstract class SignupModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    abstract fun signupViewModel(viewModel: SignupViewModel):ViewModel
}