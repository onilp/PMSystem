package com.example.pmsystem.di.component

import com.example.pmsystem.MainActivity
import com.example.pmsystem.di.module.RetrofitModule
import com.example.pmsystem.di.module.SharedPreferencesModule
import com.example.pmsystem.manager.employeelist.EmployeeListViewModel
import com.example.pmsystem.project.createproject.CreateProjectViewModel
import com.example.pmsystem.project.home.HomeFragment
import com.example.pmsystem.project.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, SharedPreferencesModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(createProjectViewModel: CreateProjectViewModel)
    fun inject(employeeListViewModel: EmployeeListViewModel)
}