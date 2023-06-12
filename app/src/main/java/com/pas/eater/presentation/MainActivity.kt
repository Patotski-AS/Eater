package com.pas.eater.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_LABELED
import com.pas.eater.R
import com.pas.eater.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.tbHome?.toolbarHome)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding?.let {
            it.tbHome.tvCity.text = "Санкт-Петербург"
            it.tbHome.tvData.text = "12 августа, 2023"
        }

        navigationSettings()
    }

    private fun navigationSettings() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView? = binding?.navView
        navView?.labelVisibilityMode = LABEL_VISIBILITY_LABELED

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_category,
                R.id.navigation_basket,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener {_, destination, _->
            when(destination.id) {
                R.id.navigation_category->binding?.tbHome?.toolbarHome?.visibility = View.GONE
                else->binding?.tbHome?.toolbarHome?.visibility = View.VISIBLE
            }
        }

        navView?.setupWithNavController(navController)
    }

}
