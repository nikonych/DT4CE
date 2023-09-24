package com.example.dt4ce.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.dt4ce.R
import com.example.dt4ce.network.RemoteDataSource
import com.example.dt4ce.network.UserApi
import com.example.dt4ce.preferences.UserPreferences
import com.example.dt4ce.repository.BaseRepository
import kotlinx.coroutines.launch


abstract class BaseFragment<VM: BaseViewModel,B: ViewBinding,Repository: BaseRepository> : Fragment() {

    protected lateinit var binding : B
    protected  val remoteDataSource = RemoteDataSource()
    protected lateinit var viewModel: VM
    protected lateinit var userPreferences: UserPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            userPreferences = UserPreferences(requireContext())
            binding = getFragmentBinding(inflater,container)
            val factory = ViewModelFactory(getFragmentRepository())
            viewModel = ViewModelProvider(this, factory).get(getViewModel())
            return binding.root
    }

    fun logout() = lifecycleScope.launch{
        val authtoken = userPreferences.authToken
        val  api = remoteDataSource.buildApi(UserApi::class.java, authtoken)
        viewModel.logout(api)
        userPreferences.clear()
        findNavController().navigate(R.id.loginFragment)
    }

    abstract fun getViewModel() : Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?): B
    abstract fun getFragmentRepository(): Repository
}
