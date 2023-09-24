package com.example.dt4ce.ui.initiative

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentInitiativeBinding
import com.example.dt4ce.network.InitiativesApi
import com.example.dt4ce.network.Resource
import com.example.dt4ce.network.model.Content
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.ui.base.BaseFragment
import com.squareup.picasso.Picasso


class InitiativeFragment :
    BaseFragment<InitiativeViewModel, FragmentInitiativeBinding, InitiativesRepository>() {

    private var id: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        id = requireArguments().getInt("id", 0)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initiativeResponse.observe(viewLifecycleOwner, Observer {
            Log.d("gg", it.toString())
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value)
                }

                is Resource.loading -> {
                }

                is Resource.Failure -> {
                    findNavController().navigate(R.id.loginFragment)
                }

                else -> {}
            }

        })
        viewModel.getInitiative(id)
        viewModel.qrResponse.observe(viewLifecycleOwner, Observer {
            Log.d("gg", it.toString())
            when (it) {
                is Resource.Success -> {
                    Log.d("gg", it.toString())
                }

                is Resource.loading -> {
                }

                is Resource.Failure -> {
                    Log.d("gg", it.toString())

                }

                else -> {}
            }

        })
    }

    private fun updateUI(value: Content) {
        with(binding) {
            tvTitle.text = value.title
            tvDescription.text = value.description
            tvCreator.text = "Автор: " + (value.user?.surname ?: "") + " " + (value.user?.name ?: "")
            tvDateCreate.text = "Дата: " + value.createdAt
            tvVotesCount.text = "Количество голосов: " + value.for_votes_count.toString()
            tvViewsCount.text = "Количество просмотров: " + value.views_count.toString()
            Picasso.get().load(value.imageUrls?.get(0) ?: "https://t4.ftcdn.net/jpg/01/39/65/03/360_F_139650302_ztH64Rwgvuc8joC7xJR57MeefepwcDUS.jpg").into(image);

            btnGetQr.setOnClickListener {
                value.id?.let { it1 -> viewModel.getQR(it1) }

            }
        }
    }

    override fun getViewModel() = InitiativeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInitiativeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): InitiativesRepository {
        val authtoken = userPreferences.authToken
        return InitiativesRepository(
            remoteDataSource.buildApi(
                InitiativesApi::class.java,
                authtoken
            )
        )
    }


}