package com.strikalov.indexfeargreed.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.strikalov.indexfeargreed.App

import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.presenter.InfoFragmentPresenter
import com.strikalov.indexfeargreed.view.InfoFragmentView
import javax.inject.Inject


class InfoFragment : MvpAppCompatFragment(), InfoFragmentView {

    @Inject
    @InjectPresenter
    lateinit var infoFragmentPresenter: InfoFragmentPresenter

    @ProvidePresenter
    fun providePresenter() = infoFragmentPresenter

    init {
        App.getAppComponent().injectInfoFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

}
