package com.example.assignmentaps.base.injection.module;

import com.example.assignmentaps.base.AssignmentAPSApplication
import com.example.assignmentaps.networking.BaseRetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = arrayOf(AndroidInjectionModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: AssignmentAPSApplication): AssignmentAPSApplication {
        return application
    }

    @Provides
    @Singleton
    fun provideBaseRetrofitBuilder(): BaseRetrofitBuilder {
        return BaseRetrofitBuilder()
    }

}
