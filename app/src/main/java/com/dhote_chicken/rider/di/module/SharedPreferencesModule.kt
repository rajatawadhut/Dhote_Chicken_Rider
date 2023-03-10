package com.dhote_chicken.rider.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefEditor(sharedPreferences: SharedPreferences) : SharedPreferences.Editor{
        return sharedPreferences.edit()
    }

}