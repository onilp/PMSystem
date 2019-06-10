package com.example.pmsystem.project.home

import android.app.ProgressDialog
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.BuildConfig
import com.example.pmsystem.R
import com.example.pmsystem.adapter.ProjectRecyclerViewAdapter
import com.example.pmsystem.developer.showtasklist.ShowTaskListFragment
import com.example.pmsystem.manager.task.TaskListFragment
import com.example.pmsystem.model.project.ProjectListResponse
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
    lateinit var recyclerViewDecoration: DividerItemDecoration
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        init()

        projectListLiveData = homeViewModel.fetchProject()
        //subscribe to view model
        projectListLiveData.observe(this, Observer {
            progressDialog.show()

            if(it == null){
                progressDialog.dismiss()

                no_project_tv.visibility = View.VISIBLE
                projectRecyclerView.visibility = View.GONE
            }else {
                progressDialog.dismiss()

                no_project_tv.visibility = View.GONE
                projectRecyclerView.visibility = View.VISIBLE
                projectAdapter = ProjectRecyclerViewAdapter(
                    context!!, it.projects.toList().asReversed()
                )
                projectRecyclerView.adapter = projectAdapter

                projectAdapter.onItemClick = {
                    if(BuildConfig.FLAVOR.equals("manager")){
                        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TaskListFragment()).addToBackStack(null).commit()
                    }else{
                        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ShowTaskListFragment()).addToBackStack(null).commit()
                    }
                }
            }
        })
        return rootView
    }

    private fun init() {
        //set toolbar title
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.project)

        projectRecyclerView = rootView.findViewById(R.id.project_recyclerview) as RecyclerView
        projectRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        projectRecyclerView.addItemDecoration(recyclerViewDecoration)

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading projects...")

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}
