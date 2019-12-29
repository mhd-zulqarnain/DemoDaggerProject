package com.zulqarnain.testproject.di.component

import android.app.Application
import com.zulqarnain.testproject.di.BaseApplication
import com.zulqarnain.testproject.di.module.*
import dagger.BindsInstance
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
        ActivityBindingModule::class,
        RoomModule::class,
        RepositoryModule::class
    ]
)
interface BaseComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: BaseApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): BaseComponent
    }

}