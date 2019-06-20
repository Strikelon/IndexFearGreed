package com.strikalov.indexfeargreed.presenter

import com.strikalov.indexfeargreed.view.MainView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    lateinit var mainPresenter: MainPresenter

    @Mock
    lateinit var mainView: MainView

    @Before
    fun setUp(){
        mainPresenter = MainPresenter()
        mainPresenter.attachView(mainView)
    }

    @Test
    fun onNavigationItemSelectedTest(){

        val destinationId = 1

        mainPresenter.onNavigationItemSelected(destinationId)

        Mockito.verify(mainView).navigate(destinationId)

    }

}