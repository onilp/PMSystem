package com.example.pmsystem.manager.subtask

import android.util.Log
import com.example.pmsystem.model.SubTaskResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateSubTaskPresenter(var view: CreateSubTaskContract.View) : CreateSubTaskContract.Presenter {
    override fun buttonClicked(project_id: String,task_id: String, sub_task_name: String, sub_task_status: String, sub_task_desc: String, sub_start_date: String, sub_end_date: String) {

        val apiInterface = ApiInterface.getRetrofitInstance()
            .createSubTask(project_id,task_id,sub_task_name,sub_task_status,sub_task_desc,sub_start_date,sub_end_date)
        apiInterface.enqueue(object : Callback<SubTaskResponse>{
            override fun onFailure(call: Call<SubTaskResponse>, t: Throwable) {
                Log.e("on Error",t.message)
                view.showCreateSubTaskFail(t.message)
            }

            override fun onResponse(call: Call<SubTaskResponse>, response: Response<SubTaskResponse>) {
                Log.e("on Error",response.body()?.msg?.get(0).toString())
                if (response.body()?.msg?.get(0).toString().equals("sub task successfully created")){
                    view.showCreateSubTaskSuccess(response.body())
                    view.listAllSubTask()
                }else if (response.body()?.msg?.get(0).toString().equals("project not found")){
                    view.showCreateSubTaskFail(response.body()?.msg?.get(0).toString())

                }

            }

        }


        )


    }
}