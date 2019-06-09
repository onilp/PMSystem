package com.example.pmsystem.manager.subtask

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SubTaskListViewModel : ViewModel(){
    @Inject
    lateinit var apiInterface: ApiInterface

    var subTaskList : MutableLiveData<List<SubTaskListResponse.ProjectSubTask>> = MutableLiveData()

    fun requestSubTaskList() : MutableLiveData<List<SubTaskListResponse.ProjectSubTask>>{
        MyApplication.component.inject(this)
        apiInterface.getSubTaskList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                subTaskList.value = it.project_sub_task
                Log.e("SubTaskListVM--", it.project_sub_task.toString())
            }, {
                //subTaskList.value = null
                Log.e("SubTaskListVM--", it.message)
            })
        return subTaskList
    }

}
