package com.example.pmsystem.manager.home

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.project.ProjectListResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var apiInterface: ApiInterface

    lateinit var projectList: MutableLiveData<ProjectListResponse>

    @SuppressLint("CheckResult")
    fun fetchProject(): MutableLiveData<ProjectListResponse> {

        MyApplication.component.inject(this)

        projectList = MutableLiveData()

        apiInterface.getProjectList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                projectList.value = it
                Log.d("Fetch project", it.toString())
            }, {
                projectList.value = null
                Log.e("Failed project", it.message)
            })

        return projectList
    }
}