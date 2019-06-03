package com.example.pmsystem.project.Home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.ProjectRecyclerViewAdapter
import com.example.pmsystem.R
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragment : Fragment(), HomeContract.View {
//    @Inject
//    lateinit var apiInterface: ApiInterface
//    lateinit var component: ApplicationComponent

    lateinit var homePresenter: HomePresenter
    lateinit var projectAdapter: ProjectRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        homePresenter = HomePresenter.newInstance()
        val project_recyclerview = rootView.findViewById(R.id.project_recyclerview) as RecyclerView
        project_recyclerview.layoutManager = LinearLayoutManager(context)

//        getProjectList()
//        component = DaggerApplicationComponent.builder().retrofitModule(RetrofitModule()).build()
//        component.injectRetrofit(activity@MainActivity())
        val apiInterface = ApiInterface.getRetrofitInstance()
            .getProjectList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.d("Project response", it.toString())
                projectAdapter = ProjectRecyclerViewAdapter(context!!, it?.projects!!.toList())
                project_recyclerview.adapter = projectAdapter
            }, { it ->
                Log.e("Error", it.message)
            })


        return rootView
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private fun getProjectList() {
//        homePresenter.getProjects()
//        component = DaggerApplicationComponent.builder().retrofitModule(RetrofitModule()).build()
//        component.injectRetrofit(view)
    }

}
