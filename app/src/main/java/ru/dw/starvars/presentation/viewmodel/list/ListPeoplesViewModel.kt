package ru.dw.starvars.presentation.viewmodel.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.room.PeopleListMapper
import ru.dw.starvars.domain.usecases.GetListPeoplesRequestUrlUseCase
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.usecases.GetAllPeoples
import ru.dw.starvars.domain.usecases.RefreshBaseDataUseCases
import ru.dw.starvars.presentation.view.list.ListState


class ListPeoplesViewModel : ViewModel() {
    private var liveData: MutableLiveData<ListState> = MutableLiveData()
    private val repository = MyApp.repository
    private val getAllPeoples = GetAllPeoples(repository)
    private val refreshBaseDataUseCases = RefreshBaseDataUseCases(repository)
    private val getListPeoplesRequestUrlUseCase = GetListPeoplesRequestUrlUseCase(repository)

    init {
        observeRoom()
    }

    fun refresh() = refreshBaseDataUseCases

    private fun observeRoom() {
        getAllPeoples.invoke().observeForever { list ->
            val listItemView = mutableListOf<PeoplesItemView>()
            list.forEach { entity ->
                listItemView.add(PeopleListMapper().mapDbModelToEntity(entity))
            }
            liveData.postValue(ListState.Success(listItemView))
        }
    }


    fun getLivedata(): LiveData<ListState> = liveData

    fun requestUrl(url: String) {
        liveData.value = ListState.Loading

        getListPeoplesRequestUrlUseCase(url,  object : ResponseCallBackViewModel {
            override fun success(listPeoplesItemView: List<PeoplesItemView>) {
                liveData.postValue(ListState.Success(listPeoplesItemView))

            }
            override fun error(error: String) {
                liveData.postValue(ListState.Error(error))
            }
        })

    }

    interface ResponseCallBackViewModel {
        fun success(listPeoplesItemView: List<PeoplesItemView>)
        fun error(error: String)
    }

}