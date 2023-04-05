package com.github.linkav20.streaky.ui.userprofile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.DI
import com.github.linkav20.streaky.di.ScreenScope
import com.github.linkav20.streaky.di.ViewModelFactory
import com.github.linkav20.streaky.di.ViewModelKey
import com.github.linkav20.streaky.ui.mytaskslist.DaggerMyTasksListComponent
import com.github.linkav20.streaky.ui.mytaskslist.MyTaskListViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap


@Component(modules = [UserProfileModule::class])
@ScreenScope
interface UserProfileComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): UserProfileComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerUserProfileComponent.builder().appContext(DI.appComponent.appContext())
                .api(DI.networkComponent.api()).build()
        }
    }
}


@Module
abstract class UserProfileModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun userProfileViewModel(viewModel: UserProfileViewModel): ViewModel
}