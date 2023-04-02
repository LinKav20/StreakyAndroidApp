package com.github.linkav20.streaky.ui.myfriendtaskslist

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


@Component(modules = [MyFriendTasksListModule::class])
@ScreenScope
interface  MyFriendTasksListComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): MyFriendTasksListComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerMyFriendTasksListComponent.builder().appContext(DI.appComponent.appContext())
                .api(DI.networkComponent.api()).build()
        }
    }
}

@Module
abstract class MyFriendTasksListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyFriendTaskListViewModel::class)
    abstract fun creationTaskViewModel(viewModel: MyFriendTaskListViewModel): ViewModel
}