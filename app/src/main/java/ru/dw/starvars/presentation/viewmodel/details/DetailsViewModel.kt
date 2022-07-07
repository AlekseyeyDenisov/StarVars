package ru.dw.starvars.presentation.viewmodel.details

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import ru.dw.starvars.MyApp
import ru.dw.starvars.domain.repository.RepositoryDetails
import ru.dw.starvars.domain.usecases.GetValueAttrUseCases


class DetailsViewModel : ViewModel() {
    private val repository: RepositoryDetails = MyApp.repository
    private val getValueAttrUseCases = GetValueAttrUseCases(repository)


    fun getNameAttr(url: String, isInternet: Boolean, name: (String) -> Unit) {
        Thread {
            val valueRoom = getValueAttrUseCases(url)
            if (valueRoom != null) {
                Handler(Looper.getMainLooper()).post {
                    name(valueRoom.name)
                }
            } else if (isInternet) {
                repository.getRequestPlanetUrl(url) { value ->
                    Handler(Looper.getMainLooper()).post {
                        name(value)
                    }
                }
            }
        }.start()

    }
}
