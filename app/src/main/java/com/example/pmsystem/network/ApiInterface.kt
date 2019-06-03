package com.example.pmsystem.network

import com.example.pmsystem.model.LoginResponse
import com.example.pmsystem.model.RegistrationResponse
import com.example.pmsystem.model.SubTaskResponse
import com.example.pmsystem.model.TaskResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @GET("pms_create_task.php?")
    fun createTask(@Query ("project_id") project_id : String,
                   @Query("task_name") task_name : String,
                   @Query("task_status") task_status : String,
                   @Query("task_desc") task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<TaskResponse>




    @GET("pms_create_task.php?")
    fun createSubTask(@Query ("project_id") project_id : String,
                   @Query("task_id") task_id : String,
                   @Query("sub_task_name") sub_task_name : String,
                   @Query("sub_task_status") sub_task_status : String,
                   @Query("sub_task_desc") sub_task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<SubTaskResponse>




    @FormUrlEncoded
    @POST("pms_reg.php?")
    fun registerUser(
        @Field("first_name") firstname: String,
        @Field("last_name") lastname: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("company_size") companysize: String,
        @Field("your_role") yourrole: String

    ) : Call<RegistrationResponse>


    @FormUrlEncoded
    @POST("pms_login.php?")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String


    ) : Call<LoginResponse>




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