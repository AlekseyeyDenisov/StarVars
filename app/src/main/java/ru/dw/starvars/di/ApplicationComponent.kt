package ru.dw.starvars.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.dw.starvars.pressentation.view.details.DetailsFragment
import ru.dw.starvars.pressentation.view.list.ListCharactersFragment


@ApplicationScopeSingleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {


    fun inject(listCharactersFragment: ListCharactersFragment)
    fun inject(detailsFragment: DetailsFragment)


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ):ApplicationComponent

    }

}