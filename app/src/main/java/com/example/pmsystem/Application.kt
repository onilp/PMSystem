package com.example.pmsystem

import android.app.Application
import com.example.pmsystem.di.component.ApplicationComponent
import com.example.pmsystem.di.component.DaggerApplicationComponent
import com.example.pmsystem.di.module.RetrofitModule

class Application : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().build()
    }

    fun getComponent(): ApplicationComponent {
        return component
    }

}