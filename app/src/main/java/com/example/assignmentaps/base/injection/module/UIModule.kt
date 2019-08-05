package com.example.assignmentaps.base.injection.module

import com.example.assignmentaps.view.activity.MainActivity
import com.example.assignmentaps.view.injection.activities.MainActivityModule
import com.example.sumit.kotlinapplication.base.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class UIModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

}