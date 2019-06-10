package com.strikalov.indexfeargreed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractorImpl
import com.strikalov.indexfeargreed.model.network.WebApi
import com.strikalov.indexfeargreed.model.repositories.NetworkRepository
import com.strikalov.indexfeargreed.model.repositories.NetworkRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onNext

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG_LOG = "MainActivityLog"
    }

    private lateinit var webApi: WebApi
    private lateinit var networkRepository: NetworkRepository
    private lateinit var fearGreedIndexInteractor: FearGreedIndexInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webApi = WebApi()
        networkRepository = NetworkRepositoryImpl(webApi)
        fearGreedIndexInteractor = FearGreedIndexInteractorImpl(networkRepository)

        fearGreedIndexInteractor.loadFearGreedIndexList(7)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(TAG_LOG, it.fearGreedIndexList.toString())
                },
                {
                    Log.i(TAG_LOG, it.toString())
                }
            )

    }
}
