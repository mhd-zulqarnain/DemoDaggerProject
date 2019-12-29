package com.zulqarnain.testproject.di.module

import androidx.lifecycle.ViewModel
import com.zulqarnain.testproject.architecture.ViewModelKey
import com.zulqarnain.testproject.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainVMmodule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun productDetailsViewModel(productViewModel: MainViewModel): ViewModel
}