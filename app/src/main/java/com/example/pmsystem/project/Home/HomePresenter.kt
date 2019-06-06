package com.example.pmsystem.project.Home

import com.example.pmsystem.di.component.ApplicationComponent
import com.example.pmsystem.network.ApiInterface
import javax.inject.Inject

class HomePresenter: HomeContract.Presenter {

    @Inject
    lateinit var apiInterface: ApiInterface
    lateinit var component: ApplicationComponent

    override fun getProjects() {
//        component = MyApplication().getComponent()
//        apiInterface.getProjectList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({it ->
//                Log.d("Project response", it[0].toString())
//            }, {it ->
//                Log.e("Error", it.message)
//            })
    }

    companion object{
        fun newInstance(): HomePresenter {
            val homePresenter = HomePresenter()
            return homePresenter
        }
    }

}