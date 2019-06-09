package com.example.pmsystem.manager.employeelist

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.employee.Employee
import com.example.pmsystem.model.employee.EmployeeResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import javax.inject.Inject

class EmployeeListViewModel: ViewModel(), AnkoLogger {
    @Inject
    lateinit var apiInterface: ApiInterface

    lateinit var employeeList: MutableLiveData<List<Employee>>

    @SuppressLint("CheckResult")
    fun fetchEmployee(): MutableLiveData<List<Employee>>{
        MyApplication.component.inject(this)

        employeeList = MutableLiveData()

        apiInterface.getEmployeeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                debug(it.toString())
                employeeList.value = it.employees
            }, {
                error(it.message)
                employeeList.value = null
            })

        return employeeList
    }
}