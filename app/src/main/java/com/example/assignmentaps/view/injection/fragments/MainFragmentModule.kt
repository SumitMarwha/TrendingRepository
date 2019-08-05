package com.example.assignmentaps.view.injection.fragments

import com.example.assignmentaps.base.injection.module.fragments.BaseFragmentModule
import dagger.Module

@Module(includes = arrayOf(BaseFragmentModule::class))
abstract class MainFragmentModule {

}