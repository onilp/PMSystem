package com.example.pmsystem.adapter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.R
import com.example.pmsystem.developer.updatesubtask.UpdateSubTaskFragment
import com.example.pmsystem.model.SubTaskListResponse
import com.example.pmsystem.model.project.Project
import kotlinx.android.synthetic.main.item_sub_task_list.view.*

class SubTaskListAdapter(val context : Context, val datalist : List<SubTaskListResponse.ProjectSubTask>) :
    RecyclerView.Adapter<SubTaskListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sub_task_list,parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datalist.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindValue(datalist.get(position))
        holder.itemView.setOnClickListener{
            var prefs : SharedPreferences = context.getSharedPreferences("SubTaskUpdate",0)
            prefs.edit().putString("taskId",datalist.get(position).taskid).apply()
            prefs.edit().putString("subTaskId",datalist.get(position).subtaskid).apply()
            prefs.edit().putString("projectId",datalist.get(position).projectid).apply()
            prefs.edit().putString("subTaskStatus",datalist.get(position).subtaskstatus).apply()
            holder.itemView.setOnClickListener {

            }
        }

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindValue(data: SubTaskListResponse.ProjectSubTask) {
            itemView.subtaskid.text = data.subtaskid.toString()
            itemView.taskid.text = data.taskid.toString().toString()
            itemView.projectid.text = data.projectid.toString()
            itemView.subtaskname.text = data.subtaskname.toString()

            itemView.subtaskstatus.text = data.subtaskstatus.toString()
            itemView.subtaskdesc.text = data.subtaskdesc.toString().toString()
            itemView.startdate.text = data.startdate.toString()
            itemView.endstart.text = data.endstart.toString()

        }
    }

}