package com.example.dt4ce.ui.base

import androidx.lifecycle.ViewModel
import com.example.dt4ce.network.UserApi
import com.example.dt4ce.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel(){
suspend fun logout(api: UserApi)= repository.logout(api)
}