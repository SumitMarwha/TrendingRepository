package com.example.assignmentaps.base.injection.module.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider
import com.example.assignmentaps.view.viewmodel.MainActivityViewModel
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kotlin.Suppress;

@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
