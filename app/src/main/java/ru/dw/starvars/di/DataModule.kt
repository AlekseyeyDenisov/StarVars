package ru.dw.starvars.di


import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.DBRoom

@Module
interface DataModule {

    @ApplicationScopeSingleton
    @Binds
    fun bindLocalDataSource(impl: DBRoom): RoomDatabase

    @ApplicationScopeSingleton
    @Binds
    fun bindRemoteSource(impl: RetrofitApiStarWars): ApiRetrofitListInterface
}