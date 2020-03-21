package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.view.IndexFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@InjectViewState
@Singleton
class IndexFragmentPresenter @Inject constructor(
    val fearGreedIndexInteractor: FearGreedIndexInteractor): MvpPresenter<IndexFragmentView>(){

    companion object {
        private const val TAG_LOG = "IndexFragmentPresenter"
        const val GREED = "greed"
        const val FEAR = "fear"
        const val NEUTRAL = "neutral"
    }

    private var indexFearGreed: Int = 0
    private var indexFearGreedClassification: String? = null
    private var timeStamp: String? = null
    private var date: Date? = null
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    private var disposable: Disposable? = null

    fun onSwipeRefreshFearGreedIndex(isAnimatorRunning: Boolean){
        if(!isAnimatorRunning) {
            viewState.isOnline()
        }else{
            viewState.swipeRefreshHide()
        }
    }

    fun onClickRefreshFearGreedIndex(isAnimatorRunning: Boolean){
        if(!isAnimatorRunning) {
            viewState.swipeRefreshShow()
            viewState.isOnline()
        }
    }

    fun networkIsConnected(){
        downloadFearGreedIndex()
    }

    fun networkNotConnected(){
        viewState.swipeRefreshHide()
        viewState.showToastNoNetworkConnection()
    }

    private fun downloadFearGreedIndex(){

        disposable?.dispose()

        disposable = fearGreedIndexInteractor.loadFearGreedIndex()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.swipeRefreshHide()
                    indexFearGreed = Integer.parseInt(it.value)
                    indexFearGreedClassification = it.valueClassification
                    date = Date(it.timestamp * 1000)
                    date?.let {
                        timeStamp = dateFormat.format(date)
                    }
                    viewState.setIndexValueWithAnimation(indexFearGreed)
                },
                {
                    Log.i(TAG_LOG, it.toString())
                    viewState.swipeRefreshHide()
                    viewState.showErrorToast(it.toString())
                }
            )
    }

    fun onAnimationEnd(){
        setTextResult()
        setTextDate()
        viewState.setIndexValueWithOutAnimation(indexFearGreed)
    }

    private fun setTextResult(){
        indexFearGreedClassification?.let {
            when{
                it.toLowerCase().contains(GREED) -> viewState.setTextResult(GREED)
                it.toLowerCase().contains(FEAR) -> viewState.setTextResult(FEAR)
                it.toLowerCase().contains(NEUTRAL) -> viewState.setTextResult(NEUTRAL)
            }
        }
    }

    private fun setTextDate(){
        timeStamp?.let {
            viewState.setTextDate(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}