package com.example.pmsystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.R
import com.example.pmsystem.model.project.Project
import kotlinx.android.synthetic.main.list_project.view.*

class ProjectRecyclerViewAdapter(private val context: Context, private val projectList: List<Project>):
    RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder>() {

    lateinit var onItemClick: ((Project) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_project, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(projectList[position])

        viewHolder.itemView.setOnClickListener {
            onItemClick.invoke(projectList[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(project: Project) {
            itemView.project_name_tv.text = project.projectname
            if(project.projectstatus == "1"){
                itemView.project_status_tv.text = itemView.context.getString(R.string.start_a_project)
            }else if(project.projectstatus == "2"){
                itemView.project_status_tv.text = itemView.context.getString(R.string.not_complete)
            }else{
                itemView.project_status_tv.text = itemView.context.getString(R.string.completed)
            }
            itemView.project_desc_tv.text = project.projectdesc
            itemView.start_date_tv.text = project.startdate
            itemView.end_date_tv.text = project.endstart
        }
    }
}