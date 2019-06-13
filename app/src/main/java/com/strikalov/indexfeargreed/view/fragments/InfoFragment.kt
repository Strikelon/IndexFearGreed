package com.strikalov.indexfeargreed.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.presenter.InfoFragmentPresenter
import com.strikalov.indexfeargreed.view.InfoFragmentView


class InfoFragment : MvpAppCompatFragment(), InfoFragmentView {

    @InjectPresenter
    lateinit var infoFragmentPresenter: InfoFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

}