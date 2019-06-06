package com.example.pmsystem.manager.subtask

import com.example.pmsystem.model.SubTaskListResponse


class SubTaskListContract {

    interface View{
        fun navigateToCreateSubTask()
        fun showTaskListError(message: String?)
        fun passDatasToAdapter(projectsubTask: List<SubTaskListResponse.ProjectSubTask>) {

        }
    }
    interface Presenter{
        fun buttonClickedd()
        fun viewIsCreated()
    }
}