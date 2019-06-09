package com.example.pmsystem.manager.employeelist

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.example.pmsystem.MyApplication
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

    @SuppressLint("CheckResult")
    fun getEmployeeList(){
        MyApplication.component.inject(this)

        apiInterface.getEmployeeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                debug(it.toString())
            }, {
                error(it.message)
            })
    }
}