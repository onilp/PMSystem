package com.example.pmsystem.developer.updatesubtask

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.pmsystem.R
import kotlinx.android.synthetic.main.fragment_update_sub_task.*

class UpdateSubTaskFragment : Fragment() {

    private lateinit var rootView : View
    private lateinit var btn_update : Button
    private lateinit var update_spinner : Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_update_sub_task,container,false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.update_sub_task_status)
        var prefs = context!!.getSharedPreferences("SubTaskUpdate",0)
        var taskId = prefs.getString("taskId","")
        var subTaskId = prefs.getString("subTaskId","")
        var projectId = prefs.getString("projectId","")
        var subTaskStatus = prefs.getString("taskId","")

        /*et_ut_taskId.text = taskId
        et_ut_subtaskId.text = subTaskId
        et_ut_projectId.text = projectId*/
        update_spinner = rootView.findViewById(R.id.spinner_update_status)
        val status = arrayOf("Choose Status", "Start", "Incomplete", "Complete")
        update_spinner.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, status)

        btn_update = rootView.findViewById(R.id.btn_subtask_update)
        btn_update.setOnClickListener {
            val updateSubTaskViewModel = ViewModelProviders.of(this).get(UpdateSubTaskViewModel::class.java)
            val response = updateSubTaskViewModel.updateSubTasks(taskId,subTaskId,projectId,"70",subTaskStatus)
            response.observe(this, Observer {
                it-> Toast.makeText(context, it?.msg?.get(0), Toast.LENGTH_LONG).show()})
        }
        return rootView
    }
}