package com.zulqarnain.testproject.di

import com.zulqarnain.testproject.di.component.DaggerBaseComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication() : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerBaseComponent.builder()
            .build()
        component.inject(this)
        return component
    }
}