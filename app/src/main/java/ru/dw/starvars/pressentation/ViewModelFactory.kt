package ru.dw.starvars.pressentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>,Provider<ViewModel>>
):ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        @Suppress("UNCHECKED_CAST")
//        return viewModelProviders[modelClass]?.get() as T
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelProviders[modelClass] ?: viewModelProviders.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}