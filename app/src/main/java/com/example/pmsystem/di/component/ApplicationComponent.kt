package com.example.pmsystem.di.component

import com.example.pmsystem.MainActivity
import com.example.pmsystem.di.module.RetrofitModule
import com.example.pmsystem.di.module.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RetrofitModule::class, SharedPreferencesModule::class))
interface ApplicationComponent {
    fun injectRetrofit(mainActivity: MainActivity)

    fun injectSharedPreferences(mainActivity: MainActivity)
}