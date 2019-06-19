package com.strikalov.indexfeargreed.di

import android.app.Application
import android.content.Context
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractorImpl
import com.strikalov.indexfeargreed.model.network.WebApi
import com.strikalov.indexfeargreed.model.repositories.NetworkRepository
import com.strikalov.indexfeargreed.model.repositories.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application){

    @Provides
    @Singleton
    fun provideAppContext():Context = application.applicationContext

    @Provides
    @Singleton
    fun provideWebApi():WebApi = WebApi()

    @Provides
    @Singleton
    fun provideNetworkRepository(webApi: WebApi): NetworkRepository = NetworkRepositoryImpl(webApi)

    @Provides
    @Singleton
    fun provideFearGreedIndexInteractor(networkRepository: NetworkRepository) : FearGreedIndexInteractor =
        FearGreedIndexInteractorImpl(networkRepository)

}