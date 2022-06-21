package ru.dw.starvars.presenter.list


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.starvars.data.repositories.RepositoryIpl
import ru.dw.starvars.domain.cases.GetListPeoplesCase
import ru.dw.starvars.domain.model.PeoplesItemView

class ListPeoplesViewModel : ViewModel() {
    private val liveData: MutableLiveData<ListState> = MutableLiveData()
    private val repository = RepositoryIpl
    private val getPeoplesList = GetListPeoplesCase(repository)


    fun getLivedata(): LiveData<ListState> = liveData

    fun upDataListPeople(url: String) {
        liveData.value = ListState.Loading
        getPeoplesList.getListPeoples(url, object : ResponseCallBack {
            override fun success(listPeoplesItemView: List<PeoplesItemView>) {

                liveData.postValue(ListState.Success(listPeoplesItemView))

            }

            override fun error(error: String) {
                liveData.postValue(ListState.Error(error))
            }
        })
    }

    interface ResponseCallBack {
        fun success(listPeoplesItemView: List<PeoplesItemView>)
        fun error(error: String)
    }

}