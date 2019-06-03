package com.example.pmsystem.manager.task

import com.example.pmsystem.model.TaskListResponse

interface TaskListContract {
    interface View{
        fun navigateToCreateTask()
        fun showTaskListError(message: String?)
        fun passDataToAdapter(projectTask: List<TaskListResponse.ProjectTask>) {
        }
    }
    interface Presenter{
        fun buttonClicked()
        fun viewIsCreated()
    }
}