package com.example.pmsystem.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.pmsystem.Application
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    @Provides
    internal fun provideSharedPreferencesInstance(context: Application): SharedPreferences {
        val sharedPreferences = context.getSharedPreferences("userPre", Context.MODE_PRIVATE)
        return sharedPreferences
    }
}