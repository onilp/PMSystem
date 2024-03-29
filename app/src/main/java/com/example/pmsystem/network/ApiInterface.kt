package com.example.pmsystem.network

import com.example.pmsystem.model.*
import com.example.pmsystem.model.createproject.CreateProjectResponse
import com.example.pmsystem.model.project.ProjectListResponse
import io.reactivex.Observable
import com.example.pmsystem.model.employee.EmployeeResponse
import com.example.pmsystem.model.subtaskdetaildeveloper.SubTaskDetailDeveloperResponse
import com.example.pmsystem.model.subtaskdeveloperlist.SubTaskDeveloperListResponse
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

    @GET("pms_project_task_list.php")
    fun getTaskList() : Observable<TaskListResponse>
    //fun getTaskList() : Call<TaskListResponse>

    @GET("pms_create_project_team.php")
    fun assignPTS(@Query("project_id") project_id : String, @Query("team_member_userid") team_member_userid : String, @Query("task_id") task_id : String, @Query("subtask_id") subtask_id : String) : Call<AssignPTSResponse>

    @GET("pms_assign_task_project.php")
    fun assignTask(@Query("task_id") task_id : String, @Query("project_id") project_id : String, @Query("team_member_userid") team_member_userid: String) : Call<AssignTasksResponse>

    @GET("pms_assign_sub_task_project.php")
    fun assignSubTasks(@Query("subtask_id") subtask_id :String, @Query("task_id") task_id : String, @Query("project_id") project_id : String, @Query("team_member_userid") team_member_userid: String) : Call<AssignTasksResponse>

    @GET("pms_create_sub_task.php?")
    fun createSubTask(@Query ("project_id") project_id : String,
                   @Query("task_id") task_id : String,
                   @Query("sub_task_name") sub_task_name : String,
                   @Query("sub_task_status") sub_task_status : String,
                   @Query("sub_task_desc") sub_task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<SubTaskResponse>

    @GET("pms_project_sub_task_list.php?")
    fun getSubTaskList() : Observable<SubTaskListResponse>
    //fun getSubTaskList() : Call<SubTaskListResponse>



    @GET("pms_view_task.php?")
    fun viewTaskList(@Query ("user_id") user_id : String)
        : Call<ShowTaskListResponse>


    @GET("pms_view_task_deatil.php?")
    fun ViewTaskDetails(@Query ("taskid") taskid : String,
                        @Query ("project_id") project_id : String)
    : Call<ShowTaskDetailResponse>








    @GET("pms_create_project.php?")
    fun createProject(
        @Query("project_name") project_name: String,
        @Query("project_status") project_status: String,
        @Query("project_desc") project_desc: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): Observable<CreateProjectResponse>

    @GET("pms_projects.php?")
    fun getProjectList(): Observable<ProjectListResponse>

    @POST("pms_reg.php?")
    fun registerUser(
        @Query("first_name") firstname: String,
        @Query("last_name") lastname: String,
        @Query("email") email: String,
        @Query("mobile") mobile: String,
        @Query("password") password: String,
        @Query("company_size") companysize: String,
        @Query("your_role") yourrole: String
    ) : Call<RegistrationResponse>


    @FormUrlEncoded
    @POST("pms_login.php?")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>

    @GET("pms_employee_list.php")
    fun getEmployeeList(): Observable<EmployeeResponse>

    @GET("pms_view_subtask.php?")
    fun fetchSubTaskListDeveloper(@Query("user_id") user_id: String, @Query("taskid") taskid: String): Observable<SubTaskDeveloperListResponse>

    @GET("pms_view_sub_task_deatil.php?")
    fun fetchSubTaskDetail(@Query("taskid") taskid: String, @Query("subtask_id") subtask_id: String, @Query("project_id") project_id: String): Observable<SubTaskDetailDeveloperResponse>

    @GET("pms_edit_sub_task_status.php")
    fun updateSubtask(@Query("taskid") taskid : String,
                      @Query("subtaskid") subtaskid : String,
                      @Query("project_id") project_id : String,
                      @Query("userid") userid : String,
                      @Query("subtask_status") subtask_status : String) : Call<UpdateSubtask>


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