package com.example.pmsystem.manager.assign

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.manager.task.TaskListViewModel
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.model.project.Project
import com.example.pmsystem.model.project.ProjectListResponse
import com.example.pmsystem.project.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_assign.*
import kotlinx.android.synthetic.main.fragment_assign.view.*

class AssignFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        val assignViewModel = ViewModelProviders.of(this).get(AssignViewModel::class.java)
        var assingPTSLiveData = assignViewModel.assignPTS("242", "69", "192", "137")
        /* assingPTSLiveData.observe( this,
             Observer{
             it-> Log.e("AssignFrag--PTS", it!!.msg.get(0))
         })*/
        //TODO remove this hardcoded user id. get it from shared preference
        //
        val userid: String = "69"

        if (!projectIdSelected.equals("") && !taskIdSelected.equals("") && !subTaskIdSelected.equals("")) {
            var assignLiveData = assignViewModel.assignPTS(projectIdSelected, userid, taskIdSelected, subTaskIdSelected)
            assignLiveData.observe(this, Observer { it ->
                Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
            })
            var assignTasksLiveData = assignViewModel.assignTasks(projectIdSelected, taskIdSelected, userid)
            assignTasksLiveData.observe(this, Observer { it ->
                Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
            })

            var assignSubTasksLiveData = assignViewModel.assignSubTasks(projectIdSelected, taskIdSelected, subTaskIdSelected, userid)
            assignSubTasksLiveData.observe(this,
                Observer { it ->
                    Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
                })
        } else {

            Toast.makeText(context, "Please Assign Again", Toast.LENGTH_LONG).show()
        }
    }

    var projectList: ArrayList<String> = ArrayList()
    var tasksList: ArrayList<String> = ArrayList()
    var subTasksList: ArrayList<String> = ArrayList()
    var projects: List<Project> = ArrayList()
    var tasks: ArrayList<TaskListResponse.ProjectTask> = ArrayList()
    var subTasks: ArrayList<SubTaskListResponse.ProjectSubTask> = ArrayList()
    lateinit var projectListSpinner: Spinner
    lateinit var taskListSpinner: Spinner
    lateinit var subTaskListSpinner: Spinner
    var projectIdSelected: String = ""
    var taskIdSelected: String = ""
    var subTaskIdSelected: String = ""
    var tasksIdList: ArrayList<String> = ArrayList()
    var subTasksIdList: ArrayList<String> = ArrayList()

    var homeViewModel: HomeViewModel = HomeViewModel()
    lateinit var projectListLiveData: MutableLiveData<ProjectListResponse>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(this.context).inflate(R.layout.fragment_assign, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.assign_pts)

        projectListSpinner = view.findViewById(R.id.spinner_project_list)
        taskListSpinner = view.findViewById(R.id.spinner_task_list)
        subTaskListSpinner = view.findViewById(R.id.spinner_subtask_list)

        projectListLiveData = homeViewModel.fetchProject()
        projectListLiveData.observe(this, Observer { it ->
            projects = it!!.projects.toList()

            for (i in 0 until projects.size) {
                var item: String = projects.get(i).id + ", " + projects.get(i).projectname
                projectList.add(item)
            }
            val projectArrayAdapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, projectList)
            projectListSpinner.adapter = projectArrayAdapter
        })

        projectListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Please select a Project", Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                projectIdSelected = projects.get(position).id
                taskIdSelected = ""
                subTaskIdSelected = ""
                taskSpinner(projectIdSelected)
                taskListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(context, "Please select a Task", Toast.LENGTH_LONG).show()
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        taskIdSelected = tasks!!.get(position).taskid
                        subTaskSpinner(projectIdSelected, taskIdSelected)
                        subTaskIdSelected = ""
                        subTaskListSpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    Toast.makeText(context, "Please select a Sub Task", Toast.LENGTH_LONG).show()

                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    subTaskIdSelected = subTasksIdList.get(position)
                                }
                            }
                    }
                }
            }
        }
        return view
    }


    private fun subTaskSpinner(projectId: String, taskId: String) {
        /* var subTaskListViewModel : SubTaskListViewModel = ViewModelProviders.of(this).get(SubTaskListViewModel::class.java)
         var subTaskList : LiveData<List<SubTaskListDeveloperResponse.ProjectSubTask>> = subTaskListViewModel.fetchSubTaskList()
         subTaskList.observe(this, Observer { it->
             subTasks.clear()
             subTasksList.clear()
             subTasksIdList.clear()
             var items : List<SubTaskListDeveloperResponse.ProjectSubTask> = it.subtaskList
             for (i in 0 until items.size){
                 if(items.get(i).taskid.equals(taskId)){
                     subTasks.add(items.get(i))
                     var item : String = items.get(i).subtaskid + ", " + items.get(i).subtaskname
                     subTasksList.add(item)
                     var id: String = items.get(i).subtaskid
                     subTasksIdList.add(id)
                 }
             }
             val subTaskArrayAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,subTasksList)
             subTaskListSpinner.adapter = subTaskArrayAdapter
         })*/
    }

    private fun taskSpinner(id: String) {
//        var taskListViewModel: TaskListViewModel =
//            ViewModelProviders.of(this).get(TaskListViewModel::class.java)
//        var taskList: LiveData<List<TaskListResponse.ProjectTask>> =
//            taskListViewModel.requestTaskList(id)
//        taskList.observe(this, Observer { it ->
//            tasks.clear()
//            tasksList.clear()
//            tasksIdList.clear()
//
//            for (i in 0 until it!!.size) {
//                if (it.get(i).projectid == id) {
//                    tasks.add(it.get(i))
//
//                    var item: String = it.get(i).taskid + ", " + it.get(i).taskname
//                    tasksList.add(item)
//
//                    var id = it.get(i).taskid
//                    tasksIdList.add(id)
//                }
//            }
//            val taskArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, tasksList)
//            taskListSpinner.adapter = taskArrayAdapter
//        })

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_assign.setOnClickListener(this)
    }



}