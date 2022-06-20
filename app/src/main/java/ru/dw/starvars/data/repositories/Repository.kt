package ru.dw.starvars.data.repositories

import ru.dw.starvars.presenter.list.ListPeoplesViewModel


interface Repository {
    fun getPeoples(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBack)
}