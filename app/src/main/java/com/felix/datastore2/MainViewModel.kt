package com.felix.datastore2

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private val pref: DataStoreManager): ViewModel() {
    val vText: MutableLiveData<String> = MutableLiveData("")

    fun saveDataStore(value : String){
        viewModelScope.launch {
            pref.setPass(value)
        }
    }

    fun getDataStore(): LiveData<String>{
        return pref.getPass().asLiveData()
    }
}