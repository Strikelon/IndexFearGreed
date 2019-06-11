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
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.AnimatorSet
import android.view.animation.DecelerateInterpolator
import android.animation.ObjectAnimator
import android.view.animation.AccelerateInterpolator
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG_LOG = "MainActivityLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        custom_view.setOnClickListener {
            startAnimation()
        }

    }

    private fun startAnimation() {

        val random = Random()
        var index = 0
        index = random.nextInt(100)
        Log.i("MyLogger", "index = $index")

        val animatorFirst = ObjectAnimator
            .ofInt(custom_view, "indexFearGreedValue", 0, 100)
            .setDuration(1500)
        animatorFirst.interpolator = AccelerateInterpolator()

        val animatorSecond = ObjectAnimator
            .ofInt(custom_view, "indexFearGreedValue", 100, index)
            .setDuration(1000)
        animatorSecond.interpolator = DecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet
            .play(animatorFirst)
            .before(animatorSecond)
        animatorSet.start()

    }
}
