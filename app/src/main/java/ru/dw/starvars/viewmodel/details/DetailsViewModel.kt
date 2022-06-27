package ru.dw.starvars.viewmodel.details

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.details.RepositoryDetailsIpl

class DetailsViewModel : ViewModel() {
    private val repository: RepositoryDetails = RepositoryDetailsIpl()


    fun getNameAttr(url: String, attr: String, name: (String) -> Unit) {
        Thread {
            val valueRoom = repository.getValueAttr(url)
            if (valueRoom != null) {
                Handler(Looper.getMainLooper()).post {
                    name(valueRoom.name)
                }
            } else {
                repository.getRequestUrl(url, attr) { value ->
                    Handler(Looper.getMainLooper()).post {
                        name(value)
                    }
                }
            }
        }.start()

    }
}
