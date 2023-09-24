package com.example.dt4ce.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentRegistrationBinding
import com.example.dt4ce.network.AuthApi
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.RegistrationData
import com.example.dt4ce.repository.RegistrationRepository
import com.example.dt4ce.ui.base.BaseFragment
import com.example.dt4ce.util.visible


class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding, RegistrationRepository>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.signupResponse.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.visible(it is Resource.loading)
            Log.d("gg", it.toString())
            when(it)
            {
                is Resource.Success ->{
                    findNavController().navigate(R.id.loginFragment)
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()

                }

                is Resource.Failure ->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()

                }

                else -> {}
            }
        })

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)

        }
        binding.buttonSignup.setOnClickListener {
            signup()
        }
    }


    private fun signup(){
        val number = binding.eidttextName.text.toString().trim()
        val email = binding.eidttextEmail.text.toString().trim()
        val password = binding.eidttextPassword.text.toString().trim()
        val cpassword = binding.eidttextCpassword.text.toString().trim()

        viewModel.signup(RegistrationData(number, email, password, cpassword))

    }

    override fun getViewModel()  = RegistrationViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentRegistrationBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = RegistrationRepository(remoteDataSource.buildApi(AuthApi::class.java))


}