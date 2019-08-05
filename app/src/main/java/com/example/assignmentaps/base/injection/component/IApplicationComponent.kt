package com.example.assignmentaps.base.injection.component

import com.example.assignmentaps.base.AssignmentAPSApplication
import com.example.assignmentaps.base.injection.module.AppModule
import com.example.assignmentaps.base.injection.module.UIModule
import com.example.assignmentaps.base.injection.module.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, UIModule::class, ViewModelModule::class))
interface IApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application:AssignmentAPSApplication): Builder
        fun build(): IApplicationComponent
    }

    fun inject(application: AssignmentAPSApplication)
}