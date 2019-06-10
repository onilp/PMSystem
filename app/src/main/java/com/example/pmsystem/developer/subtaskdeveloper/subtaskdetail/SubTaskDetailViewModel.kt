package com.example.pmsystem.developer.subtaskdeveloper.subtaskdetail

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.subtaskdetaildeveloper.SubTaskDetailDeveloperResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import javax.inject.Inject

class SubTaskDetailViewModel: ViewModel(), AnkoLogger {
    @Inject
    lateinit var apiInterface: ApiInterface

    lateinit var subTaskDetailList: MutableLiveData<SubTaskDetailDeveloperResponse>

    @SuppressLint("CheckResult")
    fun fetchSubTaskDetail(taskid: String, subtask_id: String, project_id: String): MutableLiveData<SubTaskDetailDeveloperResponse> {
        MyApplication.component.inject(this)

        subTaskDetailList = MutableLiveData()

        apiInterface.fetchSubTaskDetail(taskid, subtask_id, project_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                debug(it.toString())
                subTaskDetailList.value = it
            }, {
                error(it.message)
                subTaskDetailList.value = null
            })

        return subTaskDetailList
    }
}