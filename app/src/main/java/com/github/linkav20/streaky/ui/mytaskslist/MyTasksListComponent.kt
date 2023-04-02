package com.github.linkav20.streaky.ui.mytaskslist

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.DI
import com.github.linkav20.streaky.di.ScreenScope
import com.github.linkav20.streaky.di.ViewModelFactory
import com.github.linkav20.streaky.di.ViewModelKey
import com.github.linkav20.streaky.ui.auth.DaggerAuthComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@Component(modules = [MyTasksListModule::class])
@ScreenScope
interface MyTasksListComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): MyTasksListComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerMyTasksListComponent.builder().appContext(DI.appComponent.appContext())
                .api(DI.networkComponent.api()).build()
        }
    }
}


@Module
abstract class MyTasksListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyTaskListViewModel::class)
    abstract fun myTasksViewModel(viewModel: MyTaskListViewModel): ViewModel
}