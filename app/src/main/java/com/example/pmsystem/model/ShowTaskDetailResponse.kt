package com.example.pmsystem.model

data class ShowTaskDetailResponse(
    val endstart: String,
    val projectid: String,
    val startdate: String,
    val taskdesc: String,
    val taskid: String,
    val taskname: String,
    val taskstatus: String
)