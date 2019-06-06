package com.example.pmsystem.project.Home


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.MyApplication
import com.example.pmsystem.adapter.ProjectRecyclerViewAdapter
import com.example.pmsystem.R
import com.example.pmsystem.di.component.ApplicationComponent
import com.example.pmsystem.di.component.DaggerApplicationComponent
import com.example.pmsystem.di.module.RetrofitModule
import com.example.pmsystem.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var homePresenter: HomePresenter
    lateinit var projectAdapter: ProjectRecyclerViewAdapter
    lateinit var myApplication: MyApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.project)

        homePresenter = HomePresenter.newInstance()

        val projectRecyclerView = rootView.findViewById(R.id.project_recyclerview) as RecyclerView
        projectRecyclerView.layoutManager = LinearLayoutManager(context)

        myApplication = MyApplication()
        myApplication.getComponent().inject(this)

        apiInterface.getProjectList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.d("Project response", it.toString())
                projectAdapter = ProjectRecyclerViewAdapter(
                    context!!,
                    it?.projects!!.toList()
                )
                projectRecyclerView.adapter = projectAdapter
            }, { it ->
                Log.e("Project response error", it.message)
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
