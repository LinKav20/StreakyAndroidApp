package com.github.linkav20.streaky.ui.showmytask

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


@Component(modules = [ShowMyTaskModule::class])
@ScreenScope
interface ShowMyTaskComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): ShowMyTaskComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerShowMyTaskComponent.builder().appContext(DI.appComponent.appContext())
                .api(DI.networkComponent.api()).build()
        }
    }
}


@Module
abstract class ShowMyTaskModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShowMyTaskViewModel::class)
    abstract fun showMyTaskViewModel(viewModel: ShowMyTaskViewModel): ViewModel
}