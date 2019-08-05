package com.example.assignmentaps.base;

import android.app.Activity
import android.app.Application;
import com.example.assignmentaps.base.injection.component.DaggerIApplicationComponent
import com.example.assignmentaps.base.injection.component.IApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AssignmentAPSApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var applicationComponent: IApplicationComponent

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupDagger()
    }

    companion object {
        lateinit var instance: AssignmentAPSApplication
    }

    private fun setupDagger() {
        applicationComponent = DaggerIApplicationComponent.builder().application(this).build()
        applicationComponent.inject(this)
    }
}
