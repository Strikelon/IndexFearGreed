package com.strikalov.indexfeargreed.view.fragments


import android.graphics.Paint
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.strikalov.indexfeargreed.App

import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.model.entity.ChartData
import com.strikalov.indexfeargreed.presenter.ChartFragmentPresenter
import com.strikalov.indexfeargreed.view.ChartFragmentView
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_chart.view.*

import javax.inject.Inject
import kotlin.collections.ArrayList


class ChartFragment : MvpAppCompatFragment(), ChartFragmentView, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val TAG_LOG = "ChartFragmentLog"
        private const val MAX_NUMBER_OF_HORIZONTAL_LABELS = 16
    }

    @Inject
    @InjectPresenter
    lateinit var chartFragmentPresenter : ChartFragmentPresenter

    @ProvidePresenter
    fun providePresenter() = chartFragmentPresenter

    init {
        App.getAppComponent().injectChartFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_chart, container, false)

        rootView.swipe_container.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        rootView.swipe_container.setOnRefreshListener(this)

        return rootView
    }


    override fun onRefresh() {
        chartFragmentPresenter.onSwipeRefreshFearGreedIndex()
    }

    override fun swipeRefreshShow() {
        swipe_container?.isRefreshing = true
    }

    override fun swipeRefreshHide() {
        swipe_container?.isRefreshing = false
    }

    override fun chartDescriptionShow() {
        fragment_chart_linear_layout.gravity = Gravity.CENTER_HORIZONTAL
        chart_description.visibility = View.VISIBLE
    }

    override fun chartDescriptionHide() {
        chart_description.visibility = View.GONE
    }

    override fun noNetworkConnectionMessageShow() {
        fragment_chart_linear_layout.gravity = Gravity.CENTER
        no_network_connection_message.visibility = View.VISIBLE
    }

    override fun noNetworkConnectionMessageHide() {
        no_network_connection_message.visibility = View.GONE
    }

    override fun showToastNoNetworkConnection() {
        Toast.makeText(activity, R.string.no_network_connection, Toast.LENGTH_SHORT).show()
    }

    override fun isOnline() {
        val connectivityManager = getSystemService(activity as FragmentActivity, ConnectivityManager::class.java)
        val networkInfo = connectivityManager?.activeNetworkInfo

        if(networkInfo != null && networkInfo.isConnected){
            chartFragmentPresenter.networkIsConnected()
        }else {
            chartFragmentPresenter.networkNotConnected()
        }
    }

    override fun chartHide() {
        graph_view.visibility = View.GONE
    }

    override fun setValuesInGraphAndShowChart(chartDataList: MutableList<ChartData>) {

        var dataPointList : MutableList<DataPoint> = ArrayList()

        for(chartData in chartDataList){

            dataPointList.add(DataPoint(chartData.date, chartData.indexValue.toDouble()))

            val series : LineGraphSeries<DataPoint> = LineGraphSeries(dataPointList.toTypedArray())

            series.isDrawDataPoints = true
            series.dataPointsRadius = 10f
            series.isDrawBackground = true
            series.backgroundColor = ContextCompat.getColor(activity as FragmentActivity, R.color.colorBlueGraph)

            graph_view.viewport.isYAxisBoundsManual = true
            graph_view.viewport.setMinY(0.0)
            graph_view.viewport.setMaxY(100.0)
            graph_view.viewport.isScalable = true

            graph_view.gridLabelRenderer.setHumanRounding(false)

            graph_view.addSeries(series)
            graph_view.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity as FragmentActivity)
            graph_view.gridLabelRenderer.numHorizontalLabels = 16
            graph_view.viewport.isXAxisBoundsManual = true

            graph_view.viewport.setMinX(dataPointList.first().x)
            graph_view.viewport.setMaxX(dataPointList.last().x)

            graph_view.gridLabelRenderer.verticalLabelsAlign = Paint.Align.LEFT
            graph_view.gridLabelRenderer.setHorizontalLabelsAngle(120)
            graph_view.gridLabelRenderer.reloadStyles()

            graph_view.visibility = View.VISIBLE
        }

    }
}
