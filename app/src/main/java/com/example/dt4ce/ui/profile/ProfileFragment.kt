package com.example.dt4ce.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentProfileBinding
import com.example.dt4ce.repository.BaseRepository
import com.example.dt4ce.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding, BaseRepository>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        TODO("Not yet implemented")
    }

    override fun getFragmentRepository(): BaseRepository {
        TODO("Not yet implemented")
    }


}