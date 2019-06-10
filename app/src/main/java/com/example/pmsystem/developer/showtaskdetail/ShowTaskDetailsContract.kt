package com.example.pmsystem.developer.showtaskdetail

import com.example.pmsystem.model.ShowTaskDetailResponse
import com.example.pmsystem.model.SubTaskResponse

interface ShowTaskDetailsContract {
    interface View{
        fun showTaskDetailsFail(message : String?)
        fun showTaskDetailsSuccess(response: ShowTaskDetailResponse?)

    }


    interface Presenter{
        fun taskDetailsShowed(taskid: String, project:String)

    }
}