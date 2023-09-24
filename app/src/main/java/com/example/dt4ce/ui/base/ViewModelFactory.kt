package com.example.dt4ce.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dt4ce.repository.AuthRepository
import com.example.dt4ce.repository.BaseRepository
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.repository.RegistrationRepository
import com.example.dt4ce.ui.auth.AuthViewModel
import com.example.dt4ce.ui.auth.RegistrationViewModel
import com.example.dt4ce.ui.home.HomeViewModel
import com.example.dt4ce.ui.initiative.InitiativeViewModel

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository  as AuthRepository) as T
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(repository  as RegistrationRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as InitiativesRepository) as T
            modelClass.isAssignableFrom(InitiativeViewModel::class.java) -> InitiativeViewModel(repository  as InitiativesRepository) as T
           else -> throw IllegalArgumentException("View Model Nt found")
        }
    }

}