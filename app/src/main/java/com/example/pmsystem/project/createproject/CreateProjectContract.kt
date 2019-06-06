package com.example.pmsystem.project.CreateProject

interface CreateProjectContract {
    interface View{
        fun toastCreateProject(createProjectResult: String)

        fun navigateToHome()
    }

    interface Presenter{
        fun createProject(project_name: String, project_status: String, project_desc: String, start_date: String, end_date: String)
    }
}