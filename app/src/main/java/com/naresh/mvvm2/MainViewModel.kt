package com.naresh.mvvm2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var list = MutableLiveData<ArrayList<NicePlace>>()
    private var newList = arrayListOf<NicePlace>()

    fun add(blog: NicePlace) {
        newList.add(blog)
        list.value = newList
    }

    fun remove(blog: NicePlace) {
        newList.remove(blog)
        list.value = newList
    }
}