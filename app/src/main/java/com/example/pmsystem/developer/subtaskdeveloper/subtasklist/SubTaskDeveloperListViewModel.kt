package com.example.pmsystem.developer.subtaskdeveloper.subtasklist

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.subtaskdeveloperlist.SubTaskDeveloperListResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import javax.inject.Inject

class SubTaskDeveloperListViewModel: ViewModel(), AnkoLogger {
    @Inject
    lateinit var apiInterface: ApiInterface

    lateinit var subTaskDeveloperList: MutableLiveData<SubTaskDeveloperListResponse>

    @SuppressLint("CheckResult")
    fun fetchSubTaskListDeveloper(user_id: String, taskid: String): MutableLiveData<SubTaskDeveloperListResponse> {
        MyApplication.component.inject(this)

        subTaskDeveloperList = MutableLiveData()

        apiInterface.fetchSubTaskListDeveloper(user_id, taskid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                debug(it.toString())
                Log.d("SubTask", "Adding subtask")
                subTaskDeveloperList.value = it
            }, {
                error(it.message)
                Log.d("SubTask", "Failed subtask")
                subTaskDeveloperList.value = null
            })

        return subTaskDeveloperList
    }
}