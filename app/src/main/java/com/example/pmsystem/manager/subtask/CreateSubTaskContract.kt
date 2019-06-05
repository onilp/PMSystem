package com.example.pmsystem.manager.subtask

import com.example.pmsystem.model.SubTaskResponse
import com.example.pmsystem.model.TaskResponse

interface CreateSubTaskContract {

    interface View{
        fun showCreateSubTaskFail(message : String?)
        fun showCreateSubTaskSuccess(response: SubTaskResponse?)
        fun listAllSubTask()
    }

    interface Presenter{
        fun buttonClicked(project_id: String,task_id: String, sub_task_name: String, sub_task_status: String, sub_task_desc: String, sub_start_date: String, sub_end_date: String)

    }
}