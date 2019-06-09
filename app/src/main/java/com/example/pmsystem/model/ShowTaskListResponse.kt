package com.example.pmsystem.model

import com.google.gson.annotations.SerializedName

data class ShowTaskListResponse(
    @SerializedName("view tasks")
    val view_tasks: List<ViewTask>
){
    data class ViewTask(
        val projectid: String,
        val subtaskid: String,
        val taskid: String
    )
}


