package ru.dw.starvars.di


import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.dw.starvars.data.repositories.details.RepositoryDetailsIpl
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.repositories.list.RepositoryListIpl
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.AppDataBase
import ru.dw.starvars.data.room.CharactersDao
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.domain.RepositoryList

@Module
interface DataModule {

    @Binds
    fun bindRepositoryList(impl: RepositoryListIpl): RepositoryList

    @Binds
    fun bindRepositoryDetails(impl: RepositoryDetailsIpl): RepositoryDetails


    @Binds
    fun ApiRetrofitListInterface(impl: RetrofitApiStarWars): ApiRetrofitListInterface

    companion object {
        @Provides
        fun provideCharactersDao(
            application: Application
        ): AppDataBase {
            return AppDataBase.getInstance(application)
        }
    }


}