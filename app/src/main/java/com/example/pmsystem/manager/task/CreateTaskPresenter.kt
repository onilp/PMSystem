package com.example.pmsystem.manager.task

import android.util.Log
import com.example.pmsystem.model.TaskResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateTaskPresenter (var view : CreateTaskContract.View): CreateTaskContract.Presenter{
    override fun buttonClicked(project_id: String, task_name: String, task_status: String, task_desc: String, start_date: String, end_date: String) {

        val apiInterface = ApiInterface.getRetrofitInstance().createTask(project_id,task_name,task_status,task_desc, start_date, end_date)
        apiInterface.enqueue(object : Callback<TaskResponse> {
            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                Log.e("Error--",t.message)
                view.showCreateTaskFail(t.message)

            }
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                Log.e("Response---", response.body()?.msg?.get(0).toString())
                if(response.body()?.msg?.get(0).toString().equals("task successfully created")){
                    view.showCreateTaskSuccess(response.body())
                    view.listAllTask()
                }
                else if (response.body()?.msg?.get(0).toString().equals("project not found")){
                    view.showCreateTaskFail(response.body()?.msg?.get(0).toString())
                }
            }
        })
    }
}