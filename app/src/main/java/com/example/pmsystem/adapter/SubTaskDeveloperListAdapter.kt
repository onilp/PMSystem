package com.example.pmsystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.R
import com.example.pmsystem.model.subtaskdeveloperlist.Viewsubtask
import kotlinx.android.synthetic.main.item_sub_task_developer_list.view.*

class SubTaskDeveloperListAdapter(private val context: Context, private val subTaskDeveloperList: List<Viewsubtask>):
    RecyclerView.Adapter<SubTaskDeveloperListAdapter.ViewHolder>() {

    lateinit var onItemClick: ((Viewsubtask) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sub_task_developer_list, parent, false)
        Log.d("Pos 0", subTaskDeveloperList[0].toString())
        Log.d("Pos 1", subTaskDeveloperList[1].toString())
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return subTaskDeveloperList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("Position", position.toString())
        viewHolder.bind(subTaskDeveloperList[position])

        viewHolder.itemView.setOnClickListener {
            onItemClick.invoke(subTaskDeveloperList[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(subTask: Viewsubtask) {
            Log.d("RecyclerViewItem", subTask.projectid)
            itemView.sub_task_developer_project_id_tv.text = subTask.projectid
            itemView.sub_task_developer_task_id_tv.text = subTask.taskid
            itemView.sub_task_developer_sub_task_id_tv.text = subTask.subtaskid
        }
    }
}