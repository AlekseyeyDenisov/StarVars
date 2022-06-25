package ru.dw.starvars.viewmodel.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.RepositoryIpl
import ru.dw.starvars.domain.cases.GetListPeoplesCase
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.view.list.ListState
import ru.dw.starvars.utils.mapperPeoplesEntityToPeoplesItemView


class ListPeoplesViewModel : ViewModel() {
    private var liveData: MutableLiveData<ListState> = MutableLiveData()
    private val repository = RepositoryIpl
    private val getPeoplesList = GetListPeoplesCase(repository)

    init {
        observeRoom()
    }

    fun refresh() = repository.refresh()

    private fun observeRoom() {
        repository.getAll().observeForever { list ->
            val listItemView = mutableListOf<PeoplesItemView>()
            list.forEach { peolesEntity ->
                listItemView.add(mapperPeoplesEntityToPeoplesItemView(peolesEntity))
            }
            liveData.postValue(ListState.Success(listItemView))
        }
    }


    fun getLivedata(): LiveData<ListState> = liveData

    fun requestUrl(url: String) {
        liveData.value = ListState.Loading

        getPeoplesList.getListPeoples(url,  object : ResponseCallBackViewModel {
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