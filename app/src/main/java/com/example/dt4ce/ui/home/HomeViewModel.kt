package com.example.dt4ce.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.InitiativesList
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository : InitiativesRepository): BaseViewModel(repository) {
    private val _initiativesResponse: MutableLiveData<Resource<InitiativesList>> = MutableLiveData()
    val initiativesResponse: LiveData<Resource<InitiativesList>>
        get() = _initiativesResponse

    fun getAllInitiatives(page: Int, size: Int) = viewModelScope.launch {
        _initiativesResponse.value = Resource.loading
        _initiativesResponse.value = repository.getAllInitiatives(page = page, size = size)
    }
}