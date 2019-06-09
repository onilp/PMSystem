package com.example.pmsystem.manager.task

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TaskListViewModel : ViewModel(){

    @Inject
    lateinit var apiInterface: ApiInterface

    val taskList : MutableLiveData<List<TaskListResponse.ProjectTask>> = MutableLiveData()


    fun requestTaskList(): MutableLiveData<List<TaskListResponse.ProjectTask>>{
        MyApplication.component.inject(this)
//        val apiInterface = ApiInterface.getRetrofitInstance()
        /*apiInterface.getTaskList().enqueue(object : Callback<TaskListResponse>{
            override fun onFailure(call: Call<TaskListResponse>, t: Throwable) {
                Log.e("TaskListViewModel--", t.message)
            }

            override fun onResponse(
                call: Call<TaskListResponse>,
                response: Response<TaskListResponse>
            ) {
                taskList.postValue(response.body()?.projectTask)
            }
        })*/
        apiInterface.getTaskList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                taskList.value = it.projectTask
                Log.e("TaskListVM--", it.projectTask.toString())
            },{
                taskList.value = null
                Log.e("TaskListVM--erro",it.message)
            })

        return taskList
    }
}