package com.strikalov.indexfeargreed.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.presenter.ChartFragmentPresenter
import com.strikalov.indexfeargreed.view.ChartFragmentView


class ChartFragment : MvpAppCompatFragment(), ChartFragmentView {

    @InjectPresenter
    lateinit var chartFragmentPresenter : ChartFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

}
