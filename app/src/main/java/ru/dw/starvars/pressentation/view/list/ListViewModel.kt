package ru.dw.starvars.pressentation.view.list


import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.domain.usecases.GetAllCharacterCase
import ru.dw.starvars.domain.usecases.GetListCharacterCase
import ru.dw.starvars.domain.usecases.RefreshListCharacterCase
import javax.inject.Inject


class ListViewModel @Inject constructor(
    private val getChaptersListCase: GetListCharacterCase,
    private val refreshChaptersListCase: RefreshListCharacterCase,
    private val getAllCharacterCase: GetAllCharacterCase
) : ViewModel() {


    private var liveData: MutableLiveData<ListState> = MutableLiveData()

    init {
        observeRoom()
    }

    fun getLivedata(): LiveData<ListState> = liveData

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
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