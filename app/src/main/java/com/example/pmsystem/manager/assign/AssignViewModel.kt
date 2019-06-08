package com.example.pmsystem.manager.assign


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.pmsystem.model.AssignPTSResponse
import com.example.pmsystem.model.AssignTasksResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssignViewModel : ViewModel () {

    var assignPTSResponse : MutableLiveData<AssignPTSResponse> = MutableLiveData()
    var assignTaskResponse : MutableLiveData<AssignTasksResponse> = MutableLiveData()
    var assignSubTasksResponse : MutableLiveData<AssignTasksResponse> = MutableLiveData()

    fun assignPTS(project_id : String, user_id: String, task_id: String, subtasks_id : String) : LiveData<AssignPTSResponse> {

        val apiInterface = ApiInterface.getRetrofitInstance()
        apiInterface.assignPTS(project_id, user_id, task_id, subtasks_id)
            .enqueue(object : Callback<AssignPTSResponse>{
                override fun onFailure(call: Call<AssignPTSResponse>, t: Throwable) {
                    Log.e("AssignViewModelError--",t.message)
                }

                override fun onResponse(
                    call: Call<AssignPTSResponse>,
                    response: Response<AssignPTSResponse>
                ) {
                    Log.e("AssignViewModelResponse",response.body().toString())
                    assignPTSResponse.value = response.body()
                }
            })
        return assignPTSResponse
    }

    fun assignTasks(project_id: String, task_id: String, user_id: String) : LiveData<AssignTasksResponse> {
        val apiInterface = ApiInterface.getRetrofitInstance()
        apiInterface.assignTask(project_id, user_id, task_id)
            .enqueue(object : Callback<AssignTasksResponse>{
                override fun onFailure(call: Call<AssignTasksResponse>, t: Throwable) {
                    Log.e("AssignVM -- Error", t.message)
                }

                override fun onResponse(
                    call: Call<AssignTasksResponse>,
                    response: Response<AssignTasksResponse>
                ) {
                    Log.e("AssignVM -- Response", response.body().toString())
                    assignTaskResponse.value = response.body()
                }
            })
        return assignTaskResponse

    }

    fun assignSubTasks(project_id: String, task_id: String, subtasks_id : String, user_id: String) : LiveData<AssignTasksResponse>{
        val apiInterface = ApiInterface.getRetrofitInstance()
        apiInterface.assignSubTasks(subtasks_id, task_id, project_id, user_id)
            .enqueue(object : Callback<AssignTasksResponse>{
                override fun onFailure(call: Call<AssignTasksResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<AssignTasksResponse>,
                    response: Response<AssignTasksResponse>
                ) {
                    Log.e("AssignVM -- Response", response.body().toString())
                    assignSubTasksResponse.value = response.body()

                }
            })
        return assignSubTasksResponse
    }
}