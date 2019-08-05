package com.example.assignmentaps.base.injection.module.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sumit.kotlinapplication.base.injection.scopes.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class BaseActivityModule {

    @Binds
    @PerActivity
    internal abstract fun activity(appCompatActivity: AppCompatActivity): Activity

    @Binds
    @PerActivity
    internal abstract fun activityContext(activity: Activity): Context

    @Module
    companion object {

        const val ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager"

        @JvmStatic
        @Provides
        @Named(ACTIVITY_FRAGMENT_MANAGER)
        @PerActivity
        fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }
    }
}
