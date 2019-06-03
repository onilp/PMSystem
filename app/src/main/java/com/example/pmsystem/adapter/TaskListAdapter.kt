package com.example.pmsystem.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.pmsystem.R
import com.example.pmsystem.model.TaskListResponse
import kotlinx.android.synthetic.main.item_task_list.view.*

class TaskListAdapter(var context: Context, var taskList: List<TaskListResponse.ProjectTask>) :
    RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskListAdapter.MyViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_task_list, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(p0: TaskListAdapter.MyViewHolder, p1: Int) {

        p0.bindValues(taskList.get(p1))
        p0.itemView.setOnClickListener {
            var pref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
            var editor : SharedPreferences.Editor = pref.edit()
            editor.putString("project_id",taskList.get(p1).projectid).apply()
            editor.putString("task_id", taskList.get(p1).taskid).apply()
            Toast.makeText(context, taskList.get(p1).projectid, Toast.LENGTH_LONG).show()
            //TODO navigate to the List sub task fragment (BIN)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindValues(taskList: TaskListResponse.ProjectTask) {
            itemView.tv_taskid.text = "Task Id: " + taskList.taskid
            itemView.tv_projectid.text = "Project Id: " + taskList.projectid
            itemView.tv_taskname.text = "Task Name: " + taskList.taskname
            itemView.tv_taskstatus.text = "Status: " + taskList.taskstatus
            itemView.tv_taskdesc.text = "Description: " + taskList.taskdesc
            itemView.tv_taskstartdate.text = "Start Date: " + taskList.startdate
            itemView.tv_taskenddate.text = "End date: " + taskList.endstart
        }

    }
}