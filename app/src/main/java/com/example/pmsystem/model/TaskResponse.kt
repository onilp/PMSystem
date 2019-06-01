package com.example.pmsystem.model

import com.google.gson.annotations.SerializedName

data class TaskResponse(

	@field:SerializedName("msg")
	val msg: List<String?>? = null,

	@field:SerializedName("project_id")
	val projectId: String? = null,

	@field:SerializedName("task_id")
	val taskId: Int? = null
)