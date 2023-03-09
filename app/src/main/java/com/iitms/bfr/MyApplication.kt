package com.iitms.bfr

import com.iitms.bfr.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlin.properties.Delegates

class MyApplication : DaggerApplication() {
//    http://172.16.4.32/digitalevaluation/api/
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).baseUrl("http://dhotebackendapi-env.eba-zrpsmzfd.ap-south-1.elasticbeanstalk.com:8800/").databaseKey("").build()
    }

    companion object {
        private val TAG = MyApplication::class.java.simpleName
        var instance: MyApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}