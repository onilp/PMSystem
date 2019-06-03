package com.example.pmsystem.manager.task

import android.util.Log
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListPresenter (var view : TaskListContract.View) : TaskListContract.Presenter {
    override fun buttonClicked() {
        view.navigateToCreateTask()
    }

    override fun viewIsCreated() {
        val apiInterface = ApiInterface.getRetrofitInstance().getTaskList()
        apiInterface.enqueue(object : Callback<TaskListResponse> {
            override fun onFailure(call: Call<TaskListResponse>, t: Throwable) {
                Log.e("TaskListError---:", t.message)
                view.showTaskListError(t.message)
            }
            override fun onResponse(call: Call<TaskListResponse>, response: Response<TaskListResponse>) {
                Log.e("TaskListResponse--", response.body()!!.projectTask.toString())
                view.passDataToAdapter(response.body()!!.projectTask)
            }
        })
        //view.listAllTasks()
    }

}