package com.example.pmsystem.developer.updatesubtask

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.pmsystem.model.UpdateSubtask
import com.example.pmsystem.network.ApiInterface.Companion.getRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateSubTaskViewModel {


    var subtasksResponse = MutableLiveData<UpdateSubtask>()


    fun updateSubTasks(subid: String, tid: String, pid: String, uid: String, status : String) : MutableLiveData<UpdateSubtask> {

        val apiInterface = getRetrofitInstance()

        val updateSubTaskCall = apiInterface.updateSubtask(tid, subid, pid, uid, status)
        updateSubTaskCall.enqueue(object : Callback<UpdateSubtask> {
            override fun onFailure(call: Call<UpdateSubtask>, t: Throwable) {
                Log.e("UpdtSubTaskVMerror--", t.message)
            }

            override fun onResponse(call: Call<UpdateSubtask>, response: Response<UpdateSubtask>) {
                Log.e("UpdtSubTaskVMResponse--", response.body().toString())
                subtasksResponse.value = response.body()
            }
        })
        return subtasksResponse
    }
}