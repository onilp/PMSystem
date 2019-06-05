package com.example.pmsystem.model

import com.google.gson.annotations.SerializedName

data class SubTaskListResponse(

    @SerializedName("project sub task")
    val project_sub_task: List<ProjectSubTask>
){

data class ProjectSubTask(
    val endstart: String,
    val projectid: String,
    val startdate: String,
    val subtaskdesc: String,
    val subtaskid: String,
    val subtaskname: String,
    val subtaskstatus: String,
    val taskid: String
)

}