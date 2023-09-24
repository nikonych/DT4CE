package com.example.dt4ce.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.LoginData
import com.example.dt4ce.repository.AuthRepository
import com.example.dt4ce.responces.LoginResponse
import com.example.dt4ce.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private  val repository: AuthRepository
) : BaseViewModel(repository) {
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        loginData: LoginData
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.loading
        _loginResponse.value = repository.login(loginData)
    }

   suspend fun saveAuthToken(token: String)  {
        repository.saveAuthToken(token)
    }

}