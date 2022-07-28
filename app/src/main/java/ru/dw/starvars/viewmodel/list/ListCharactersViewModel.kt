package ru.dw.starvars.viewmodel.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.list.RepositoryIpl
import ru.dw.starvars.domain.cases.GetAllCharacterCase
import ru.dw.starvars.domain.cases.GetListCharacterCase
import ru.dw.starvars.domain.cases.RefreshListCharacterCase
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.utils.mapperPeoplesEntityToPeoplesItemView
import ru.dw.starvars.view.list.ListState


class ListCharactersViewModel : ViewModel() {
    private var liveData: MutableLiveData<ListState> = MutableLiveData()
    private val repository = RepositoryIpl()
    private val getChaptersListCase = GetListCharacterCase(repository)
    private val refreshChaptersListCase = RefreshListCharacterCase(repository)
    private val getAllCharacterCase = GetAllCharacterCase(repository)

    init {
        observeRoom()
    }

    fun refresh() = refreshChaptersListCase.invoke()

    private fun observeRoom() {
        getAllCharacterCase.invoke().observeForever { list ->
            val listItemView = mutableListOf<CharacterItemView>()
            list.forEach { entity ->
                listItemView.add(mapperPeoplesEntityToPeoplesItemView(entity))
            }
            liveData.postValue(ListState.Success(listItemView))
        }
    }


    fun getLivedata(): LiveData<ListState> = liveData

    fun requestUrl(url: String) {
        liveData.value = ListState.Loading

        getChaptersListCase.invoke(url,  object : ResponseCallBackViewModel {
            override fun success(listCharacterItemView: List<CharacterItemView>) {
                liveData.postValue(ListState.Success(listCharacterItemView))

            }
            override fun error(error: String) {
                liveData.postValue(ListState.Error(error))
            }
        })

    }

    interface ResponseCallBackViewModel {
        fun success(listCharacterItemView: List<CharacterItemView>)
        fun error(error: String)
    }

}