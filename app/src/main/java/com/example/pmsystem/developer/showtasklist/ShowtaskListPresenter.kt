package com.example.pmsystem.developer.showtasklist

import android.content.SharedPreferences
import android.util.Log
import com.example.pmsystem.MyApplication
import com.example.pmsystem.adapter.SubTaskListAdapter
import com.example.pmsystem.model.ShowTaskListResponse
import com.example.pmsystem.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ShowtaskListPresenter(var view: ShowTaskListContract.View) : ShowTaskListContract.Presenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun viewIsCreated() {


       MyApplication.component.inject(this)

        var userid = sharedPreferences.getString("userid","")

        val apiInterface = ApiInterface.getRetrofitInstance().viewTaskList("70")
        apiInterface.enqueue(object : Callback<ShowTaskListResponse>{
            override fun onResponse(call: Call<ShowTaskListResponse>, response: Response<ShowTaskListResponse>) {
                Log.e("show task list response", response.body()!!.view_tasks.toString())


                view.passDataToAdapter(response.body()!!.view_tasks)

            }

            override fun onFailure(call: Call<ShowTaskListResponse>, t: Throwable) {
                Log.e("show task list error", t.message)
                view.showTaskListError(t.message)

            }


        }


        )

    }
}