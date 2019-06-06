package com.example.pmsystem.di.component

import com.example.pmsystem.MainActivity
import com.example.pmsystem.di.module.RetrofitModule
import com.example.pmsystem.di.module.SharedPreferencesModule
import com.example.pmsystem.project.Home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, SharedPreferencesModule::class])
interface ApplicationComponent {
    fun inject(homeFragment: HomeFragment)
}