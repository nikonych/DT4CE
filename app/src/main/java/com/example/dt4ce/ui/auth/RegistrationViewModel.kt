package com.example.dt4ce.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.RegistrationData
import com.example.dt4ce.repository.RegistrationRepository
import com.example.dt4ce.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import okhttp3.ResponseBody

class RegistrationViewModel(
    private val repository: RegistrationRepository
): BaseViewModel(repository) {
    private val _signupResponse: MutableLiveData<Resource<ResponseBody>> = MutableLiveData()
    val signupResponse: LiveData<Resource<ResponseBody>>
        get() = _signupResponse

    fun signup(
        registrationData: RegistrationData
    ) = viewModelScope.launch {
        _signupResponse.value = Resource.loading
        _signupResponse.value = repository.signup(registrationData)
    }
}