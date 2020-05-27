package com.saulgargar.epicrew.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.di.injectFeatures
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectFeatures()

        initBottomNavBar()
        setBottomVisivility()
        window.navigationBarColor = resources.getColor(R.color.full_black)
    }

    private fun initBottomNavBar(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun setBottomVisivility(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.nameFinderFragment -> {
                    showBottomNav()
                }
                R.id.expertFinderFragment -> {
                    showBottomNav()
                }
                R.id.creaturesFinderFragment -> {
                    showBottomNav()
                }
                else -> {
                    hideBottomNav()
                }
            }
        }
    }

    private fun showBottomNav(){
        nav_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        nav_view.visibility = View.GONE
    }

}
