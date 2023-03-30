package com.github.linkav20.streaky.ui.creationtask

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

@Component(modules = [CreationTaskModule::class])
@ScreenScope
interface  CreationTaskComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): CreationTaskComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerCreationTaskComponent.builder().appContext(DI.appComponent.appContext()).build()
        }
    }
}

@Module
abstract class CreationTaskModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreationTaskViewModel::class)
    abstract fun creationTaskViewModel(viewModel: CreationTaskViewModel): ViewModel
}