package com.mvk.events.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvk.events.BR
import com.mvk.events.R
import com.mvk.events.databinding.ActivityMainBinding
import com.mvk.events.di.component.ActivityComponent
import com.mvk.events.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun provideDataBindingVariable(): Int = BR.mainVM

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()
    }

}