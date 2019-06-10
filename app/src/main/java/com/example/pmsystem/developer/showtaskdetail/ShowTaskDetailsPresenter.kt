package com.example.pmsystem.developer.showtaskdetail

import android.os.Bundle
import android.util.Log
import com.example.pmsystem.model.ShowTaskDetailResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShowTaskDetailsPresenter(var view : ShowTaskDetailsContract.View) : ShowTaskDetailsContract.Presenter{
    override fun taskDetailsShowed(taskid: String, projectid: String) {




        val apiInterface = ApiInterface.getRetrofitInstance().ViewTaskDetails(taskid,projectid)

        apiInterface.enqueue(object : Callback<ShowTaskDetailResponse>{
            override fun onResponse(call: Call<ShowTaskDetailResponse>, response: Response<ShowTaskDetailResponse>) {
                Log.e("show details success",response.body()!!.toString())

                view.showTaskDetailsSuccess(response.body()!!)


            }

            override fun onFailure(call: Call<ShowTaskDetailResponse>, t: Throwable) {
                Log.e("show details fail",t.message)
                view.showTaskDetailsFail(t.message)
            }
        }

        )

    }

}