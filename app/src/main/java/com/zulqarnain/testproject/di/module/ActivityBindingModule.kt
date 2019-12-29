package com.zulqarnain.testproject.di.module

import com.zulqarnain.testproject.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule{

    @ContributesAndroidInjector(modules = [MainActivityFragmentBindingModule::class])
    fun bindMainActivity():MainActivity
}
