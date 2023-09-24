package com.example.dt4ce.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentLoginBinding
import com.example.dt4ce.network.AuthApi
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.LoginData
import com.example.dt4ce.repository.AuthRepository
import com.example.dt4ce.ui.base.BaseFragment
import com.example.dt4ce.util.enable
import com.example.dt4ce.util.handleApiError
import com.example.dt4ce.util.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignin.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.loading)

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.access_token)
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }
                }
                is Resource.Failure -> handleApiError(it){login()}
                else -> {}
            }
        })

        binding.eidttextPassword.addTextChangedListener {
            val email = binding.eidttextEmail.text.toString().trim()
            binding.buttonSignin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }



        binding.buttonSignin.setOnClickListener {
            login()
        }

        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }
    }


    private fun login(){
        val email = binding.eidttextEmail.text.toString().trim()
        val password = binding.eidttextPassword.text.toString().trim()
        viewModel.login(LoginData(email, password))
    }
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)

}