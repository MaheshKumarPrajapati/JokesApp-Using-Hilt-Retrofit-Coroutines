package com.example.jokesapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.repository.MainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private val _res = MutableLiveData<String>()
    val res : LiveData<String>
        get() = _res

    val timer = (0..Int.MAX_VALUE)
        .asSequence()
        .asFlow()
        .onEach { delay(6_000) }

    init {
        getJokes()
    }

    private fun getJokes()  = viewModelScope.launch {
            timer.collect {
                mainRepository.getJokes().let {
                    _res.postValue( it.body())
                }
            }
    }

}