package com.iitms.bfr.di.module

import com.iitms.bfr.ui.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectOrderStatusFragment(): OrderStatusFragment

}