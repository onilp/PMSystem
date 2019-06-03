package com.example.pmsystem.model


import com.google.gson.annotations.SerializedName

data class TaskListResponse(
    @SerializedName("project task")
    val projectTask: List<ProjectTask>
) {
    data class ProjectTask(
        @SerializedName("endstart")
        val endstart: String,
        @SerializedName("projectid")
        val projectid: String,
        @SerializedName("startdate")
        val startdate: String,
        @SerializedName("taskdesc")
        val taskdesc: String,
        @SerializedName("taskid")
        val taskid: String,
        @SerializedName("taskname")
        val taskname: String,
        @SerializedName("taskstatus")
        val taskstatus: String
    )
}