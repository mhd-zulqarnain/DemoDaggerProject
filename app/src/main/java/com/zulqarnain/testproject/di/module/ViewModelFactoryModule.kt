package com.zulqarnain.testproject.di.module

import dagger.Module
import androidx.lifecycle.ViewModelProvider
import com.zulqarnain.testproject.architecture.ViewModelFactory
import dagger.Binds
import javax.inject.Singleton


@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
