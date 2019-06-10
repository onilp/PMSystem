package com.example.pmsystem.developer.subtaskdeveloper.subtasklist


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.MyApplication
import com.example.pmsystem.R
import com.example.pmsystem.adapter.SubTaskDeveloperListAdapter
import com.example.pmsystem.developer.subtaskdeveloper.subtaskdetail.SubTaskDetailFragment
import com.example.pmsystem.model.subtaskdeveloperlist.SubTaskDeveloperListResponse
import kotlinx.android.synthetic.main.fragment_sub_task_developer_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class SubTaskDeveloperListFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var subTaskDeveloperListAdapter:SubTaskDeveloperListAdapter
    private lateinit var subTaskDeveloperRecyclerView: RecyclerView
    private lateinit var recyclerViewDecoration: DividerItemDecoration
    private lateinit var rootView: View
    private lateinit var subTaskDeveloperListViewModel: SubTaskDeveloperListViewModel
    lateinit var subTaskDeveloperListLiveData: MutableLiveData<SubTaskDeveloperListResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sub_task_developer_list, container, false)

        init()

        subTaskDeveloperListLiveData = subTaskDeveloperListViewModel.fetchSubTaskListDeveloper(sharedPreferences.getString("userid", "")!!, arguments!!.getString("taskid", ""))
//        subTaskDeveloperListLiveData = subTaskDeveloperListViewModel.fetchSubTaskListDeveloper("70", "149")


        subTaskDeveloperListLiveData.observe(this, Observer{
            if(it == null){
                no_sub_task_developer_tv.visibility = View.VISIBLE
                subTaskDeveloperRecyclerView.visibility = View.GONE
            }else{
                Log.d("IT data", it.toString())
                no_sub_task_developer_tv.visibility = View.GONE
                subTaskDeveloperRecyclerView.visibility = View.VISIBLE

                subTaskDeveloperListAdapter = SubTaskDeveloperListAdapter(context!!, it.viewsubtasks.toList())
                subTaskDeveloperRecyclerView.adapter = subTaskDeveloperListAdapter

                subTaskDeveloperListAdapter.onItemClick = { it ->
                    val subTaskDetailFragment = SubTaskDetailFragment()
                    val bundle = Bundle()
                    bundle.putString("taskid", it.taskid)
                    bundle.putString("subtask_id", it.subtaskid)
                    bundle.putString("project_id", it.projectid)
                    subTaskDetailFragment.arguments = bundle

                    activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, subTaskDetailFragment).addToBackStack(null).commit()
                }
            }
        })

        return rootView
    }

    private fun init() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Sub Task"

        MyApplication.component.inject(this)

        subTaskDeveloperRecyclerView = rootView.findViewById(R.id.sub_task_developer_list_rv) as RecyclerView
        subTaskDeveloperRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        subTaskDeveloperRecyclerView.addItemDecoration(recyclerViewDecoration)

        subTaskDeveloperListViewModel = ViewModelProviders.of(this).get(SubTaskDeveloperListViewModel::class.java)
    }


}
