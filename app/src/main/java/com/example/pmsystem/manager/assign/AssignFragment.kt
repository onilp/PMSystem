package com.example.pmsystem.manager.assign

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.manager.task.TaskListViewModel
import com.example.pmsystem.model.TaskListResponse
import kotlinx.android.synthetic.main.fragment_assign.*
import kotlinx.android.synthetic.main.fragment_assign.view.*

class AssignFragment : Fragment(), AdapterView.OnItemSelectedListener,View.OnClickListener {

    lateinit var projectList: ArrayList<String>
    lateinit var taskList : ArrayList<String>
    lateinit var subTaskList : ArrayList<String>

    var projectIdSelected: String = ""
    var taskIdSelected : String = ""
    var subTaskIdSelected : String =""

    var tasksIdList: ArrayList<String> = ArrayList()
    var subTasksIdList: ArrayList<String> = ArrayList()

    var tasks = ArrayList<TaskListResponse.ProjectTask>()
    //lateinit var homeViewModel : HomeViewModel





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(this.context).inflate(R.layout.fragment_assign,container,false)



        var project_list = arrayOf("project 1","project 2", "project 3")

        var sub_task_list = arrayOf("sub task 1","sub task 2", "sub task 3")


        view.spinner_project_list.onItemSelectedListener
        view.spinner_task_list.onItemSelectedListener
        view.spinner_subtask_list.onItemSelectedListener



        var taskListViewModel : TaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        var taskList : LiveData<List<TaskListResponse.ProjectTask>> = taskListViewModel.requestTaskList()
        taskList.observe(this, Observer { it->
            if (it != null) {
                for (i in 0 until it.size)
                    tasks.add(it.get(i))

            }
            val taskArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, tasks)
            taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.spinner_task_list!!.adapter = taskArrayAdapter
        })






        val projectArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, project_list)
        //val taskArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, task_list)
        val subTaskArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, sub_task_list)

        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //taskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subTaskArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        view.spinner_project_list!!.adapter = projectArrayAdapter
        //view.spinner_task_list!!.adapter = taskArrayAdapter
        view.spinner_subtask_list!!.adapter = subTaskArrayAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_assign.setOnClickListener(this)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this.context,"Please select a Project",Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        val assignViewModel = ViewModelProviders.of(this).get(AssignViewModel::class.java)
        /*var assingPTSLiveData = assignViewModel.assignPTS("242","69","192","137")
        assingPTSLiveData.observe( this,
            Observer{
            it-> Log.e("AssignFrag--PTS", it!!.msg.get(0))
        })*/
        //TODO remove this hardcoded user id. get it from shared preference
        //
        val userid : String = "69"



    }

}
