package com.example.pmsystem.manager.task

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListViewModel : ViewModel(){
    val taskList : MutableLiveData<List<TaskListResponse.ProjectTask>> = MutableLiveData()


    fun requestTaskList() : MutableLiveData<List<TaskListResponse.ProjectTask>>{
        val apiInterface = ApiInterface.getRetrofitInstance()
        apiInterface.getTaskList().enqueue(object : Callback<TaskListResponse>{
            override fun onFailure(call: Call<TaskListResponse>, t: Throwable) {
                Log.e("TaskListViewModel--", t.message)
            }

            override fun onResponse(
                call: Call<TaskListResponse>,
                response: Response<TaskListResponse>
            ) {
                taskList.postValue(response.body()?.projectTask)
            }
        })

        return taskList
    }
}