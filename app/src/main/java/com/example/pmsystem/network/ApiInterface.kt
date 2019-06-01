package com.example.pmsystem.network

import com.example.pmsystem.model.TaskResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("pms_create_task.php?")
    fun createTask(@Query ("project_id") project_id : String,
                   @Query("task_name") task_name : String,
                   @Query("task_status") task_status : String,
                   @Query("task_desc") task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<TaskResponse>


    companion object{
        val BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/"
        fun getRetrofitInstance() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}