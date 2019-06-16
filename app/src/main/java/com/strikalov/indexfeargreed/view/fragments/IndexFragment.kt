package com.strikalov.indexfeargreed.view.fragments


import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.getColor
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.strikalov.indexfeargreed.App

import com.strikalov.indexfeargreed.R
import com.strikalov.indexfeargreed.presenter.IndexFragmentPresenter
import com.strikalov.indexfeargreed.view.IndexFragmentView
import kotlinx.android.synthetic.main.fragment_index.*
import kotlinx.android.synthetic.main.fragment_index.view.*
import javax.inject.Inject

class IndexFragment : MvpAppCompatFragment(), IndexFragmentView, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val TAG_LOG = "IndexFragmentLog"
    }

    @Inject
    @InjectPresenter
    lateinit var indexFragmentPresenter: IndexFragmentPresenter

    @ProvidePresenter
    fun providePresenter() = indexFragmentPresenter

    init {
        App.getAppComponent().injectIndexFragment(this)
    }

    private val animatorSet = AnimatorSet()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_index, container, false)

        rootView.swipe_container.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        rootView.swipe_container.setOnRefreshListener(this)

        rootView.custom_view.setOnClickListener {
            indexFragmentPresenter.onClickRefreshFearGreedIndex(animatorSet.isRunning)
        }

        return rootView
    }

    override fun onRefresh() {
        indexFragmentPresenter.onSwipeRefreshFearGreedIndex(animatorSet.isRunning)
    }

    override fun swipeRefreshShow() {
        swipe_container?.isRefreshing = true
    }

    override fun swipeRefreshHide() {
        swipe_container?.isRefreshing = false
    }

    override fun isOnline() {
        val connectivityManager = getSystemService(activity as FragmentActivity,ConnectivityManager::class.java)
        val networkInfo = connectivityManager?.activeNetworkInfo

        if(networkInfo != null && networkInfo.isConnected){
            indexFragmentPresenter.networkIsConnected()
        }else {
            indexFragmentPresenter.networkNotConnected()
        }

    }

    override fun setTextResult(indexClassification: String) {

        when (indexClassification) {
            IndexFragmentPresenter.GREED -> {
                index_result_text_view?.setTextColor(getColor(activity as FragmentActivity, R.color.colorGreen))
                index_result_text_view?.text = getString(R.string.greed)
            }
            IndexFragmentPresenter.FEAR -> {
                index_result_text_view?.setTextColor(getColor(activity as FragmentActivity, R.color.colorRed))
                index_result_text_view?.text = getString(R.string.fear)
            }
            IndexFragmentPresenter.NEUTRAL -> {
                index_result_text_view?.setTextColor(getColor(activity as FragmentActivity, R.color.lightGrayTextColor))
                index_result_text_view?.text = getString(R.string.neutral)
            }
        }

    }

    override fun setTextDate(date: String) {
        text_date?.text = date
    }

    override fun showToastNoNetworkConnection() {
        Toast.makeText(activity, R.string.no_network_connection, Toast.LENGTH_SHORT).show()
    }

    override fun setIndexValueWithOutAnimation(indexValue: Int) {
        custom_view.indexFearGreedValue = indexValue
    }

    override fun setIndexValueWithAnimation(indexValue: Int) {

        val animatorFirst = ObjectAnimator
            .ofInt(custom_view, "indexFearGreedValue", 0, 100)
            .setDuration(1500)

        animatorFirst.interpolator = AccelerateInterpolator()

        val animatorSecond = ObjectAnimator
            .ofInt(custom_view, "indexFearGreedValue", 100, indexValue)
            .setDuration(1000)

        animatorSecond.interpolator = DecelerateInterpolator()

        animatorSecond.addListener(object :Animator.AnimatorListener{

            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                indexFragmentPresenter.onAnimationEnd()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        animatorSet
            .play(animatorFirst)
            .before(animatorSecond)

        animatorSet.start()

    }
}
