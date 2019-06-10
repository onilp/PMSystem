package com.example.pmsystem.developer.updatesubtask

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.pmsystem.MyApplication
import com.example.pmsystem.R
import com.example.pmsystem.di.component.ApplicationComponent
import com.example.pmsystem.di.component.DaggerApplicationComponent
import com.example.pmsystem.di.module.SharedPreferencesModule
import kotlinx.android.synthetic.main.fragment_update_sub_task.*
import kotlinx.android.synthetic.main.fragment_update_sub_task.view.*
import javax.inject.Inject


class UpdateSubTaskFragment : Fragment() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var rootView : View
    private lateinit var btn_update : Button
    private lateinit var update_spinner : Spinner
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_update_sub_task,container,false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.update_sub_task_status)

        MyApplication.component.inject(this)
        var userId = sharedPreferences.getString("userid","")
        rootView.tv_ust_userId.text = userId

        var prefs : SharedPreferences = context!!.getSharedPreferences("SubTaskUpdate",0)
        var taskId = prefs.getString("taskId","")
        var subTaskId = prefs.getString("subTaskId","")
        var projectId = prefs.getString("projectId","")
        var subTaskStatus = prefs.getString("taskId","")
        rootView.tv_ut_taskId.text = taskId
        rootView.tv_ut_subtaskId.text = subTaskId
        rootView.tv_ut_projectId.text = projectId

        update_spinner =rootView.findViewById(R.id.spinner_update_status)
        val status = arrayOf("Choose Status", "Start", "Incomplete", "Complete")
        var selectedStatus = ""
        update_spinner.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, status)
        update_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(status.get(position).equals("Start"))
                {
                    selectedStatus = "1"
                }
                else if(status.get(position).equals("Incomplete")){
                    selectedStatus = "2"
                }
                else if(status.get(position).equals("Complete")){
                    selectedStatus = "3"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context,"Please Select Action", Toast.LENGTH_LONG).show()
            }

        }
        btn_update = rootView.findViewById(R.id.btn_subtask_update)
        btn_update.setOnClickListener {

            val updateSubTaskViewModel = ViewModelProviders.of(this).get(UpdateSubTaskViewModel::class.java)
            val response = updateSubTaskViewModel.updateSubTasks(taskId,subTaskId,projectId,userId,selectedStatus)
            response.observe(this, Observer {
                it-> Toast.makeText(context, it?.msg?.get(0), Toast.LENGTH_LONG).show()})
        }
        return rootView
    }
}