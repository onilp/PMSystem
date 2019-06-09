package com.example.pmsystem.project.createproject

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateProjectViewModel: ViewModel() {

    @Inject
    lateinit var apiInterface: ApiInterface

    private var shouldNav: Boolean = false

    private var toastMsg: String = "Fill in something!"

    @SuppressLint("CheckResult")
    fun createProject(project_name: String, project_status: String, project_desc: String, start_date: String, end_date: String): Boolean {
        MyApplication.component.inject(this)

        apiInterface.createProject(project_name, project_status, project_desc, start_date, end_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("create response", it.toString())
                shouldNav = true
                toastMsg = it.msg[0]
            }, {
                Log.d("create response false", it.toString())
                shouldNav = false
                toastMsg = "Failed to create project"
            })

        return shouldNav
    }

    fun displayCreateProjectMsg(): String{
        return toastMsg
    }
}