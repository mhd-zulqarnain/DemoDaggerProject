package com.zulqarnain.testproject.di.component

import com.zulqarnain.testproject.di.module.ActivityBindingModule
import com.zulqarnain.testproject.di.module.AppModule
import com.zulqarnain.testproject.di.module.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        ActivityBindingModule::class
    ]
)
interface BaseComponent : AndroidInjector<DaggerApplication> {

}