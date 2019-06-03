package com.example.pmsystem.di.module

import android.app.Application
import com.example.pmsystem.network.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class RetrofitModule {
    var BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/"

    @Provides
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

//    @Provides
//    internal fun provideAPIInterface(retrofit: Retrofit): ApiInterface{
//
//    }
}