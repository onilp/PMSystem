package com.example.pmsystem.project.createproject

import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateProjectPresenter(private var view: CreateProjectContract.View): CreateProjectContract.Presenter {

    override fun createProject(project_name: String, project_status: String, project_desc: String, start_date: String, end_date: String) {
        val apiInterface = ApiInterface.getRetrofitInstance()
            .createProject(project_name, project_status, project_desc, start_date, end_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                view.toastCreateProject(it.msg[0])
                view.navigateToHome()
            }, { it ->
                view.toastCreateProject("Failed to create project")
            })
    }

    companion object{
        fun newInstance(view: CreateProjectContract.View): CreateProjectPresenter{
            return CreateProjectPresenter(view)
        }
    }
}