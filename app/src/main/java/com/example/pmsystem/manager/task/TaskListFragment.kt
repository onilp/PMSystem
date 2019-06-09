package com.example.pmsystem.manager.task

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.adapter.TaskListAdapter
import com.example.pmsystem.manager.assign.AssignFragment
import com.example.pmsystem.manager.task.CreateTaskFragment
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.fragment_task_list.view.*
import org.jetbrains.anko.AnkoLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListFragment : Fragment(),  AnkoLogger {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: TaskListAdapter
    private var tasks : ArrayList<TaskListResponse.ProjectTask> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_task_list, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.task_list)
            var taskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
            var taskList : LiveData<List<TaskListResponse.ProjectTask>> = taskListViewModel.requestTaskList()
            taskList.observe(this, Observer {it->
                if (it != null) {
                    for(i in 0 until it.size) {
                        tasks.add(it.get(i))
                    }
                    myAdapter = TaskListAdapter(context!!.applicationContext, tasks)
                    recyclerView.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()

                }
            })
        view.btn_create_task.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container,CreateTaskFragment())?.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewTaskList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

    }
}
