package ru.dw.starvars.pressentation.view.list


import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun getLivedata(): LiveData<ListState> = liveData

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO){
            refreshChaptersListCase.invoke()
        }
    }

    fun requestUrl(url: String) {
        liveData.value = ListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getChaptersListCase.invoke(url) { error ->
                liveData.postValue(ListState.Error(error))
            }
        }
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

}