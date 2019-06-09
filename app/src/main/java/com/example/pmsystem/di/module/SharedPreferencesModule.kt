package com.example.pmsystem.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(private val applicationContext: Context) {

    @Provides
    internal fun providesSharedPreferences(): SharedPreferences {
        return applicationContext.getSharedPreferences("userPref", Context.MODE_PRIVATE)
    }
}