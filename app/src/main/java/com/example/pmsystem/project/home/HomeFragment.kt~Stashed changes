package com.example.pmsystem.project.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pmsystem.R
import com.example.pmsystem.adapter.ProjectRecyclerViewAdapter
import com.example.pmsystem.manager.assign.AssignFragment
import com.example.pmsystem.model.project.ProjectListResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var rootView: View
    private lateinit var projectAdapter: ProjectRecyclerViewAdapter
    private lateinit var homeViewModel: HomeViewModel
    lateinit var projectListLiveData: MutableLiveData<ProjectListResponse>
    lateinit var projectRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        init()

        projectListLiveData = homeViewModel.fetchProject()
        //subscribe to view model
        projectListLiveData.observe(this, Observer {
            if(it == null){
                no_project_tv.visibility = View.VISIBLE
                projectRecyclerView.visibility = View.GONE
            }else {
                no_project_tv.visibility = View.GONE
                projectRecyclerView.visibility = View.VISIBLE
                projectAdapter = ProjectRecyclerViewAdapter(
                    context!!, it.projects.toList()
                )
                projectRecyclerView.adapter = projectAdapter
            }
        })
        return rootView
    }

    private fun init() {
        //set toolbar title
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.project)

        projectRecyclerView = rootView.findViewById(R.id.project_recyclerview) as RecyclerView
        projectRecyclerView.layoutManager = LinearLayoutManager(context)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}
