package com.example.dt4ce

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dt4ce.databinding.ActivityMainBinding
import com.example.dt4ce.preferences.UserPreferences
import com.example.dt4ce.ui.home.HomeFragmentDirections


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userPreferences = UserPreferences(this)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        userPreferences.authToken.observe(this, Observer {
            if (it == null) {
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        })
//
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_profile
            )
        )
        val bottonNavFragments = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_profile,
            R.id.initiativeFragment
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottonNavFragments.contains(destination.id)
            if (destination.id == R.id.loginFragment || destination.id == R.id.registrationFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
        navView.setupWithNavController(navController)

    }


}