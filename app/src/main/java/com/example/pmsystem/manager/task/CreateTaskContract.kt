package com.example.pmsystem.manager.task

import com.example.pmsystem.model.TaskResponse

interface CreateTaskContract {

    interface View{
        fun showCreateTaskFail(message : String?)
        fun showCreateTaskSuccess(response: TaskResponse?)
        fun listAllTask()
    }

    interface Presenter{
        fun buttonClicked(project_id: String, task_name: String, task_status: String, task_desc: String, start_date: String, end_date: String)

    }
}