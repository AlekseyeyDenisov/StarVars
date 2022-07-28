package ru.dw.starvars.viewmodel.details

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.details.RepositoryDetailsIpl
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.domain.cases.GetAttrAllCase
import ru.dw.starvars.domain.cases.GetRequestCase

class DetailsViewModel : ViewModel() {
    private val repository: RepositoryDetails = RepositoryDetailsIpl()
    private val getAllCharacterCase = GetAttrAllCase(RepositoryDetailsIpl())
    private val getRequestUrCase = GetRequestCase(repository)


    fun getNameAttr(url: String, attr: String, name: (String) -> Unit) {
        Thread {
            val valueRoom = getAllCharacterCase.invoke(url)
            if (valueRoom != null) {
                Handler(Looper.getMainLooper()).post {
                    name(valueRoom.name)
                }
            } else {
                getRequestUrCase.invoke(url, attr) { value ->
                    Handler(Looper.getMainLooper()).post {
                        name(value)
                    }
                }
            }
        }.start()

    }
}
