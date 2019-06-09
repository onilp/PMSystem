package com.example.pmsystem.developer.showtaskdetail

import com.example.pmsystem.model.SubTaskResponse

interface ShowTaskDetailsContract {
    interface View{
        fun showTaskDetailsFail(message : String?)
        fun showTaskDetailsSuccess(response: SubTaskResponse?)
        fun listAllSubTask()
    }

    interface Presenter{
        fun buttonClicked(project_id: String,task_id: String, sub_task_name: String, sub_task_status: String, sub_task_desc: String, sub_start_date: String, sub_end_date: String)


    }
}