package com.example.dt4ce.ui.qr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.dt4ce.R
import com.example.dt4ce.databinding.FragmentQRBinding
import com.example.dt4ce.network.InitiativesApi
import com.example.dt4ce.repository.InitiativesRepository
import com.example.dt4ce.ui.base.BaseFragment


class QRFragment : BaseFragment<QRViewModel, FragmentQRBinding, InitiativesRepository>() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Проверка разрешения на доступ к камере
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), 1)
        }

        // Запуск сканирования QR-кода
//        val integrator = IntentIntegrator.forSupportFragment(this)
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//        integrator.setPrompt("Сканируйте QR-код")
//        integrator.setBeepEnabled(false)
//        integrator.setOrientationLocked(false)
//        integrator.initiateScan()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_r, container, false)
    }

    override fun getViewModel() = QRViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentQRBinding.inflate(inflater, container, false)


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