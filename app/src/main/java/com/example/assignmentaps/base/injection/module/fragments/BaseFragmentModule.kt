package com.example.assignmentaps.base.injection.module.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.sumit.kotlinapplication.base.injection.scopes.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
abstract class BaseFragmentModule {

    @Module
    companion object {
        const val FRAGMENT = "BaseFragmentModule.fragment"
        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"

        @JvmStatic
        @Provides
        @Named(CHILD_FRAGMENT_MANAGER)
        @PerFragment
        fun childFragmentManager(@Named(FRAGMENT) fragment: Fragment): FragmentManager {
            return fragment.childFragmentManager
        }
    }
}