package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.foodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this activity
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        //Setup toolbar :
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.fragment_host)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.home2))
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)

        //NavigationUI.setupActionBarWithNavController(this,navController)
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home2 -> {
                    NavigationUI.onNavDestinationSelected(it,navController)
                    navController.popBackStack(it.itemId, inclusive = false)
                }

                R.id.profile2 -> {
                    NavigationUI.onNavDestinationSelected(it,navController)
                    navController.popBackStack(it.itemId, inclusive = false)
                }

                R.id.cart2 -> {
                    NavigationUI.onNavDestinationSelected(it,navController)
                    navController.popBackStack(it.itemId, inclusive = false)
                }
            }
            true
        }

        //To change text in toolBar in any destination :
        navController.addOnDestinationChangedListener{ _,destination,_ ->
            when (destination.id) {
                R.id.home2 -> homeToolbarText()
            }
        }
    }

    // Fun To Change Text in Toolbar at main fragment:
    private fun homeToolbarText(){
        binding.toolbar.title = "Home"
    }

}