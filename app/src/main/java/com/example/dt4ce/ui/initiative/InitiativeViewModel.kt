package com.example.dt4ce.ui.initiative

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.Content
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class InitiativeViewModel(private val repository : InitiativesRepository): BaseViewModel(repository) {

    private val _initiativeResponse: MutableLiveData<Resource<Content>> = MutableLiveData()
    val initiativeResponse: LiveData<Resource<Content>>
        get() = _initiativeResponse

    private val _qrResponse: MutableLiveData<Resource<String>> = MutableLiveData()
    val qrResponse: LiveData<Resource<String>>
        get() = _qrResponse

    fun getInitiative(id: Int) = viewModelScope.launch {
        _initiativeResponse.value = Resource.loading
        _initiativeResponse.value = repository.getInitiative(id)
    }

    fun getQR(id: Int) = viewModelScope.launch {
        _qrResponse.value = Resource.loading
        _qrResponse.value = repository.getQR(id)
    }

}