package com.example.mypocasi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryLookViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ProtoRepository(application)

    fun showAll(){

    }

    fun updateValue(mesto: String, stat: String, teplota: String) = viewModelScope.launch(Dispatchers.IO){
        repository.update(mesto, stat, teplota)
    }
}