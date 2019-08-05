package com.example.assignmentaps.view.injection.activities

import android.support.v7.app.AppCompatActivity
import com.example.assignmentaps.base.injection.module.activities.BaseActivityModule
import com.example.assignmentaps.view.activity.MainActivity
import com.example.assignmentaps.view.fragment.DetailsFragment
import com.example.assignmentaps.view.fragment.MainFragment
import com.example.assignmentaps.view.injection.fragments.DetailsFragmentModule
import com.example.assignmentaps.view.injection.fragments.MainFragmentModule
import com.example.sumit.kotlinapplication.base.injection.scopes.PerActivity
import com.example.sumit.kotlinapplication.base.injection.scopes.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BaseActivityModule::class])
open abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun mainFragmentModule(): MainFragment

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(DetailsFragmentModule::class))
    abstract fun detailsFragmentModule(): DetailsFragment

    @Binds
    @PerActivity
    abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity
}