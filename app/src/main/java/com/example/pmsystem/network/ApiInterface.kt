package com.example.pmsystem.network

import com.example.pmsystem.model.CreateProjectResponse
import com.example.pmsystem.model.ProjectListResponse
import com.example.pmsystem.model.TaskResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("pms_create_task.php?")
    fun createTask(
        @Query("project_id") project_id: String,
        @Query("task_name") task_name: String,
        @Query("task_status") task_status: String,
        @Query("task_desc") task_desc: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): Call<TaskResponse>

    @GET("pms_projects.php?")
    fun getProjectList(): Observable<ProjectListResponse>

    @GET("pms_create_project.php?")
    fun createProject(
        @Query("project_name") project_name: String,
        @Query("project_status") project_status: String,
        @Query("project_desc") project_desc: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): Observable<CreateProjectResponse>

    companion object {
        val BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/"
        fun getRetrofitInstance(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}