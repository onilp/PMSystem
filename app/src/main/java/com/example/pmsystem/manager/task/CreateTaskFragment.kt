package com.example.pmsystem.manager.task

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.model.TaskResponse
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.fragment_create_task.view.*

class CreateTaskFragment : Fragment(), CreateTaskContract.View {

    lateinit var createTaskPresenter: CreateTaskPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_create_task, container, false)

        createTaskPresenter = CreateTaskPresenter(this)

        view.btn_create.setOnClickListener {
            var project_id: String = et_create_task_project_id.text.toString()
            var task_name: String = et_create_task_name.text.toString()
            var task_status: String = et_create_task_status.text.toString()
            var task_desc: String = et_create_task_description.text.toString()
            var start_date: String = et_create_task_start_date.text.toString()
            var end_date: String = et_create_task_end_date.text.toString()

            createTaskPresenter.buttonClicked(
                project_id,
                task_name,
                task_status,
                task_desc,
                start_date,
                end_date
            )
        }
        return view
    }

    override fun showCreateTaskFail(message: String?) {
        Toast.makeText(context, "Error :$message", Toast.LENGTH_LONG).show()
    }

    override fun showCreateTaskSuccess(response: TaskResponse?) {
        var pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
        var editor: SharedPreferences.Editor = pref.edit()
        //val editor = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE).edit()
        editor.putString("task_id", response!!.taskId.toString()).apply()
        editor.putString("project_id", response.projectId.toString()).apply()
        Toast.makeText(context, "Successfully Created a new Task", Toast.LENGTH_LONG).show()
    }

    override fun listAllTask() {
        val fg: Fragment = TaskListFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fg)
            ?.addToBackStack(null)?.commit()
    }
}