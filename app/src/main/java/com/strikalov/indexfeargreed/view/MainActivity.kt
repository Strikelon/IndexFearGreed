package com.strikalov.indexfeargreed.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.strikalov.indexfeargreed.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG_LOG = "MainActivityLog"
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.index_fragment_menu_item -> {
                    Log.i(TAG_LOG, "index_fragment")
                    navigate(R.id.indexFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.chart_fragment_menu_item -> {
                    Log.i(TAG_LOG, "chart_fragment")
                    navigate(R.id.chartFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.info_fragment_menu_item -> {
                    Log.i(TAG_LOG, "info_fragment")
                    navigate(R.id.infoFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

    }

    private fun navigate(@IdRes destinationId: Int){
        navController.navigate(destinationId)
    }
}
