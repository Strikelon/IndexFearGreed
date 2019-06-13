package com.strikalov.indexfeargreed.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.presenter.MainPresenter
import com.strikalov.indexfeargreed.view.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val TAG_LOG = "MainActivityLog"
    }

    @InjectPresenter
    lateinit var mainPresenter : MainPresenter

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.indexFragment -> {
                    Log.i(TAG_LOG, "index_fragment")
                    mainPresenter.onNavigationItemSelected(R.id.indexFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.chartFragment -> {
                    Log.i(TAG_LOG, "chart_fragment")
                    mainPresenter.onNavigationItemSelected(R.id.chartFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.infoFragment -> {
                    Log.i(TAG_LOG, "info_fragment")
                    mainPresenter.onNavigationItemSelected(R.id.infoFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

    }

    override fun navigate(destinationId: Int) {
        navController.navigate(destinationId)
    }
}
