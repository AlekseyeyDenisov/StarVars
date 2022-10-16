package ru.dw.starvars.pressentation.view.details

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.details.RepositoryDetailsIpl
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.domain.usecases.GetAttrAllCase
import ru.dw.starvars.domain.usecases.GetRequestCase
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getRequestUrCase: GetRequestCase,
    private val getAllCharacterCase : GetAttrAllCase
) : ViewModel() {





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
