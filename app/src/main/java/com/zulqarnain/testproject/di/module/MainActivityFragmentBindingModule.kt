package com.zulqarnain.testproject.di.module

import com.zulqarnain.testproject.di.scope.FragmentScoped
import com.zulqarnain.testproject.ui.DummyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentBindingModule{

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideDummyFragment(): DummyFragment

}