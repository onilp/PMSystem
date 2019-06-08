package com.example.pmsystem

import android.app.Application
import com.example.pmsystem.di.component.ApplicationComponent
import com.example.pmsystem.di.component.DaggerApplicationComponent
import com.example.pmsystem.di.module.RetrofitModule
import com.example.pmsystem.di.module.SharedPreferencesModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .retrofitModule(RetrofitModule)
            .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var component: ApplicationComponent
    }

    fun getComponent(): ApplicationComponent {
        return component
    }
}