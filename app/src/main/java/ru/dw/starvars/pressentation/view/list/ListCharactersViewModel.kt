package ru.dw.starvars.pressentation.view.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.list.RepositoryIpl
import ru.dw.starvars.domain.cases.GetAllCharacterCase
import ru.dw.starvars.domain.cases.GetListCharacterCase
import ru.dw.starvars.domain.cases.RefreshListCharacterCase
import ru.dw.starvars.domain.model.CharacterItemView


class ListCharactersViewModel : ViewModel() {
    private var liveData: MutableLiveData<ListState> = MutableLiveData()
    private val repository = RepositoryIpl()
    private val getChaptersListCase = GetListCharacterCase(repository)
    private val refreshChaptersListCase = RefreshListCharacterCase(repository)
    private val getAllCharacterCase = GetAllCharacterCase(repository)

    init {
        observeRoom()
    }

    private fun observeRoom() {
        liveData = MediatorLiveData<ListState>().apply {
            getAllCharacterCase.invoke().observeForever { list ->
                val listItemView = mutableListOf<CharacterItemView>()
                list.forEach { entity ->
                    listItemView.add(entity)
                }
                postValue(ListState.Success(listItemView))
            }
        }
    }

    fun refresh() = refreshChaptersListCase.invoke()

    fun getLivedata(): LiveData<ListState> = liveData

    fun requestUrl(url: String) {
        liveData.value = ListState.Loading
        getChaptersListCase.invoke(url) { error ->
            liveData.postValue(ListState.Error(error))

        }

    }

}