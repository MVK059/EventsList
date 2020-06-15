package com.mvk.events.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.mvk.events.EventsApplication
import com.mvk.events.di.component.ActivityComponent
import com.mvk.events.di.component.DaggerActivityComponent
import com.mvk.events.di.module.ActivityModule
import com.mvk.events.utils.display.Toaster
import com.mvk.events.utils.navigation.NavigationController
import javax.inject.Inject

/**
 * Reference for generics: https://kotlinlang.org/docs/reference/generics.html
 * Basically BaseActivity will take any class that extends BaseViewModel
 */
abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    lateinit var dataBinding: T

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
        setupDataBinding()
//        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
    }

    fun getViewDataBinding(): T = dataBinding

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as EventsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    private fun setupDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, provideLayoutId())
        dataBinding.setVariable(provideDataBindingVariable(), viewModel)
        dataBinding.executePendingBindings()
    }

    fun showMessage(message: String) = Toaster.show(applicationContext, message)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    protected abstract fun provideDataBindingVariable(): Int

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}