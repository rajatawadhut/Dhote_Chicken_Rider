package com.dhote_chicken.rider.di.component

import android.app.Application
import com.dhote_chicken.rider.MyApplication
import com.dhote_chicken.rider.di.factory.ViewModelFactoryModule
import com.dhote_chicken.rider.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,ApiModule::class,AppModule::class,ViewModelModule::class,
        FragmentBuilderModule::class,ViewModelFactoryModule::class, SharedPreferencesModule::class, RoomDbModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance // you'll call this when setting up Dagger
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        @BindsInstance // you'll call this when setting up Dagger
        fun databaseKey(@Named("databaseKey") databaseKey: String): Builder

        fun build(): AppComponent

    }

}
