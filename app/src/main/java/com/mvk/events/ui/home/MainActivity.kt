package com.mvk.events.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvk.events.BR
import com.mvk.events.R
import com.mvk.events.databinding.ActivityMainBinding
import com.mvk.events.di.component.ActivityComponent
import com.mvk.events.ui.base.BaseActivity
import com.mvk.events.ui.home.event.EventAdapter
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var eventAdapter: EventAdapter

    override fun provideDataBindingVariable(): Int = BR.mainVM

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, Observer {
            dataBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.event.observe(this, Observer {
            it.data?.run {
                eventAdapter.appendData(this)
            }
        })
    }

    override fun setupView(savedInstanceState: Bundle?) {
        dataBinding.rvEvents.apply {
            layoutManager = linearLayoutManager
            adapter = eventAdapter
        }
    }

}