package com.pas.eater.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_LABELED
import com.pas.eater.R
import com.pas.eater.databinding.ActivityMainBinding
import com.pas.eater.domain.use_case.GetDishesUseCase
import com.pas.eater.domain.use_case.UpdateDishesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    @Inject lateinit var updateUseCase: UpdateDishesUseCase

    @Inject lateinit var getUseCase: GetDishesUseCase

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.fakeToolbar.toolbar)

        navigationSettings()

        lifecycleScope.launch {
            updateUseCase.invoke().collect {
                Log.w("TEST", it.toString())
            }
        }

        lifecycleScope.launch {
            getUseCase.invoke().stateIn(lifecycleScope).collect {
                Log.w("TEST1", it.toString())
            }
        }
    }

    private fun navigationSettings() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView
        navView.labelVisibilityMode = LABEL_VISIBILITY_LABELED

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_category,
                R.id.navigation_basket,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
