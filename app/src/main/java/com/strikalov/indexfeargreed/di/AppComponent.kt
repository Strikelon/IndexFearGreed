package com.strikalov.indexfeargreed.di

import com.strikalov.indexfeargreed.view.activities.MainActivity
import com.strikalov.indexfeargreed.view.fragments.ChartFragment
import com.strikalov.indexfeargreed.view.fragments.IndexFragment
import com.strikalov.indexfeargreed.view.fragments.InfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

    fun injectIndexFragment(indexFragment: IndexFragment)

    fun injectChartFragment(chartFragment: ChartFragment)

    fun injectInfoFragment(infoFragment: InfoFragment)

}