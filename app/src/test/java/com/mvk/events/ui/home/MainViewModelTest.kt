package com.mvk.events.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvk.events.data.model.Event
import com.mvk.events.data.model.EventList
import com.mvk.events.data.model.EventList.EventMasterList
import com.mvk.events.data.repository.EventRepository
import com.mvk.events.ui.home.MainViewModel
import com.mvk.events.utils.network.NetworkHelper
import com.mvk.events.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var eventRepository: EventRepository

    private lateinit var testScheduler: TestScheduler

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        mainViewModel = MainViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            eventRepository
        )
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testFetchEvents() {
        // Given
        val masterList = HashMap<String, Event>()
        masterList["key"] = Event("", "", "", "", "")
        val eventList = EventMasterList(masterList)
        val event = EventList(eventList)

        Mockito.doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()

        Mockito.doReturn(Single.just(event)).`when`(eventRepository).fetchEvents()

        // When
        mainViewModel.fetchEvents()

        // Then
        testScheduler.triggerActions()
        assert(mainViewModel.loading.value == false)
    }
}