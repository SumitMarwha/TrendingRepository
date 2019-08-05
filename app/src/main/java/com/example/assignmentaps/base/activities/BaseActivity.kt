package com.example.assignmentaps.base.activities;

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity;
import com.example.assignmentaps.base.injection.module.activities.BaseActivityModule
import com.example.assignmentaps.networking.BaseRetrofitBuilder
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

open class BaseActivity: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    @field:Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    lateinit var fragmentManager: FragmentManager

    @IdRes
    private var containerViewId: Int = 0

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var baseRetofitBuilder: BaseRetrofitBuilder

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun addFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .add(containerViewId, fragment)
            .commit()
    }

    protected fun setContainer(@IdRes containerViewId: Int) {
        this.containerViewId = containerViewId
    }
}
