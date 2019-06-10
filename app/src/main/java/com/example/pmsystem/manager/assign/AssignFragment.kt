package com.example.pmsystem.manager.assign

import android.app.ProgressDialog
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.pmsystem.R
import com.example.pmsystem.manager.employeelist.EmployeeListViewModel
import com.example.pmsystem.manager.subtask.SubTaskListViewModel
import com.example.pmsystem.manager.task.TaskListViewModel
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.model.TaskListResponse
import com.example.pmsystem.model.employee.Employee
import com.example.pmsystem.model.project.Project
import com.example.pmsystem.model.project.ProjectListResponse
import com.example.pmsystem.manager.home.HomeViewModel

class AssignFragment : Fragment() {

    lateinit var progressDialog : ProgressDialog
    var projectList: ArrayList<String> = ArrayList()
    var tasksList: ArrayList<String> = ArrayList()
    var subTasksList: ArrayList<String> = ArrayList()
    var employeesList : ArrayList<String> = ArrayList()
    var projects: List<Project> = ArrayList()
    var tasks: ArrayList<TaskListResponse.ProjectTask> = ArrayList()
    var subTasks: ArrayList<SubTaskListResponse.ProjectSubTask> = ArrayList()
    var employees : ArrayList<Employee> = ArrayList()
    lateinit var projectListSpinner: Spinner
    lateinit var taskListSpinner: Spinner
    lateinit var subTaskListSpinner: Spinner
    lateinit var employeeSpinner : Spinner
    var projectIdSelected: String = ""
    var taskIdSelected: String = ""
    var subTaskIdSelected: String = ""
    var employeeIdSelected : String =""
    var tasksIdList: ArrayList<String> = ArrayList()
    var subTasksIdList: ArrayList<String> = ArrayList()
    var employeeIdList : ArrayList<String> = ArrayList()

    var homeViewModel: HomeViewModel =
        HomeViewModel()
    lateinit var projectListLiveData: MutableLiveData<ProjectListResponse>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(this.context).inflate(R.layout.fragment_assign, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.assign_pts)

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading data...")
        progressDialog.show()

        projectListSpinner = view.findViewById(R.id.spinner_project_list)
        taskListSpinner = view.findViewById(R.id.spinner_task_list)
        subTaskListSpinner = view.findViewById(R.id.spinner_subtask_list)
        employeeSpinner = view.findViewById(R.id.spinner_employee_list)

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
                                    loadEmployeeSpinner()
                                    employeeSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                            employeeIdSelected = employees.get(position).empid
                                        }

                                        override fun onNothingSelected(parent: AdapterView<*>?) {
                                            Toast.makeText(context, "Please select an Employee", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                    }
                }
            }
        }
        val assignViewModel = ViewModelProviders.of(this).get(AssignViewModel::class.java)
        var btn_assign = view.findViewById<Button>(R.id.btn_assign)
        var btn_assign_task = view.findViewById<Button>(R.id.btn_assign_task)
        var btn_assign_subtask = view.findViewById<Button>(R.id.btn_assign_subtask)

        btn_assign.setOnClickListener {
            if (!projectIdSelected.equals("") && !taskIdSelected.equals("") && !subTaskIdSelected.equals("")) {
                var assignPTSLiveData = assignViewModel.assignPTS(
                    projectIdSelected,
                    employeeIdSelected,
                    taskIdSelected,
                    subTaskIdSelected
                )
                assignPTSLiveData.observe(this, Observer { it ->
                    Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
                })
            }
        }
        btn_assign_task.setOnClickListener {
            if (!projectIdSelected.equals("") && !taskIdSelected.equals("") && !subTaskIdSelected.equals("")) {
                var assignTasksLiveData = assignViewModel.assignTasks(
                    projectIdSelected,
                    taskIdSelected,
                    employeeIdSelected
                )
                assignTasksLiveData.observe(this, Observer { it ->
                    Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
                })
            }
        }
        btn_assign_subtask.setOnClickListener {
            if (!projectIdSelected.equals("") && !taskIdSelected.equals("") && !subTaskIdSelected.equals("")) {
                var assignSubTasksLiveData = assignViewModel.assignSubTasks(
                    projectIdSelected,
                    taskIdSelected,
                    subTaskIdSelected,
                    employeeIdSelected
                )
                assignSubTasksLiveData.observe(this,
                    Observer { it ->
                        Toast.makeText(context, it!!.msg.get(0), Toast.LENGTH_LONG).show()
                    })
            }
        }
        return view
    }


    private fun subTaskSpinner(projectId: String, taskId: String) {
         var subTaskListViewModel : SubTaskListViewModel = ViewModelProviders.of(this).get(
             SubTaskListViewModel::class.java)
         var subTaskList : LiveData<List<SubTaskListResponse.ProjectSubTask>> = subTaskListViewModel.requestSubTaskList()
         subTaskList.observe(this, Observer { it->
             subTasks.clear()
             subTasksList.clear()
             subTasksIdList.clear()
             for (i in 0 until it!!.size){
                 if (it.get(i).taskid.equals(taskId)) {
                     subTasks.add(it.get(i))
                     var item: String = it.get(i).subtaskid + ", " + it.get(i).subtaskname
                     subTasksList.add(item)
                     var id = it.get(i).subtaskid
                     subTasksIdList.add(id)
                 }
             }
             val subTaskArrayAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,subTasksList)
             subTaskListSpinner.adapter = subTaskArrayAdapter
         })
    }

    private fun taskSpinner(id: String) {
        var taskListViewModel: TaskListViewModel =
            ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        var taskList: LiveData<List<TaskListResponse.ProjectTask>> =
            taskListViewModel.requestTaskList()
        taskList.observe(this, Observer { it ->
            tasks.clear()
            tasksList.clear()
            tasksIdList.clear()

           for (i in 0 until it!!.size) {
                if (it.get(i).projectid == id) {
                    tasks.add(it.get(i))

                    var item: String = it.get(i).taskid + ", " + it.get(i).taskname
                    tasksList.add(item)

                    var id = it.get(i).taskid
                    tasksIdList.add(id)
                }
            }
            val taskArrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, tasksList)
            taskListSpinner.adapter = taskArrayAdapter
        })

    }

    private fun loadEmployeeSpinner(){
        progressDialog.cancel()
        Log.e("Load employee spinner","Assign")
        var employeeListViewModel = ViewModelProviders.of(this).get(EmployeeListViewModel::class.java)
        var employeeList : LiveData<List<Employee>> = employeeListViewModel.fetchEmployee()
        employeeList.observe(this, Observer{it->
            employees.clear()
            employeesList.clear()

            for (i in 0 until it!!.size){
                employees.add(it.get(i))
                var emp : String = it.get(i).empid + "- " + it.get(i).empfirstname + ", " + it.get(i).emplastname
                employeesList.add(emp)

                var id = it.get(i).empid
                employeeIdList.add(id)
            }
            val employeeArrayAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,employeesList)
            employeeSpinner.adapter = employeeArrayAdapter

        })
    }
}