package com.example.dt4ce.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentHomeBinding
import com.example.dt4ce.network.InitiativesApi
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.Content
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.ui.base.BaseFragment
import com.example.dt4ce.ui.initiative.InitiativeFragment


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, InitiativesRepository>(), OnDetailClick {
    private lateinit var adapter: InitiativesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getUser()
        viewModel.initiativesResponse.observe(viewLifecycleOwner, Observer {
            Log.d("gg", it.toString())
            when (it) {
                is Resource.Success -> {
                    it.value.content?.let { it1 -> updateUI(it1) }
                }

                is Resource.loading -> {
                }

                is Resource.Failure -> {
                    findNavController().navigate(R.id.loginFragment)
                }

                else -> {}
            }

        })
        viewModel.getAllInitiatives(0, 5)
//        binding.buttonLogout.setOnClickListener {
//            logout()
//        }
    }

    private fun updateUI(list: List<Content>) {
        adapter = InitiativesAdapter(list, this)
        binding.rvTasks.adapter = adapter
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): InitiativesRepository {
        val authtoken = userPreferences.authToken
        return InitiativesRepository(
            remoteDataSource.buildApi(
                InitiativesApi::class.java,
                authtoken
            )
        )
    }


    override fun onClick(value: Int?) {
        val number = value // Ваше число
        val fragment = InitiativeFragment() // Замените YourTargetFragment на имя вашего целевого фрагмента
        val args = Bundle()
        if (number != null) {
            args.putInt("id", number)
        }
        findNavController().navigate(R.id.initiativeFragment, args)
    }


}