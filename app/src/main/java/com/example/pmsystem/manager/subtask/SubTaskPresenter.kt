package com.example.pmsystem.manager.subtask

import android.util.Log
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubTaskPresenter(var view : SubTaskListContract.View) :  SubTaskListContract.Presenter{
    override fun buttonClickedd() {
        view.navigateToCreateSubTask()
    }


    override fun viewIsCreated() {
        val apiInterface = ApiInterface.getRetrofitInstance().getSubTaskList()
        apiInterface.enqueue(object : Callback<SubTaskListResponse>{
            override fun onResponse(call: Call<SubTaskListResponse>, response: Response<SubTaskListResponse>) {
                Log.e("sub task response", response.body()!!.project_sub_task.toString())
                view.passDatasToAdapter(response.body()!!.project_sub_task)
            }

            override fun onFailure(call: Call<SubTaskListResponse>, t: Throwable) {
                Log.e("sub task error", t.message)
                view.showTaskListError(t.message)
            }
        }





        )

    }
}