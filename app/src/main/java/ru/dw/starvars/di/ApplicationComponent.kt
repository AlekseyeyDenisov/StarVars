package ru.dw.starvars.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.dw.starvars.pressentation.view.details.DetailsFragment
import ru.dw.starvars.pressentation.view.list.ListFragment


@ApplicationScopeSingleton
@Component(
    modules = [
        DataModule::class,
        ViewModeModule::class
    ]
)
interface ApplicationComponent {

    fun inject(listFragment: ListFragment)

    fun inject(detailsFragment: DetailsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ):ApplicationComponent

    }

}