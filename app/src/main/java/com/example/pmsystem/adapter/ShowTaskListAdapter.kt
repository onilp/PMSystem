package com.example.pmsystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.R
import com.example.pmsystem.model.ShowTaskListResponse
import com.example.pmsystem.model.TaskListResponse
import kotlinx.android.synthetic.main.fragment_show_task_list.view.*
import kotlinx.android.synthetic.main.item_sub_task_list.view.*
import kotlinx.android.synthetic.main.show_task_list_item.view.*

class ShowTaskListAdapter(var context: Context, var showtasklists: List<ShowTaskListResponse.ViewTask>)
    : RecyclerView.Adapter<ShowTaskListAdapter.MyViewHolder>(){


    lateinit var onItemClick: ((ShowTaskListResponse.ViewTask) -> Unit)




    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.show_task_list_item, p0, false)
        return ShowTaskListAdapter.MyViewHolder(view)

    }

    override fun getItemCount(): Int {

        return showtasklists.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindValue(showtasklists.get(position))
        holder.itemView.setOnClickListener {
            onItemClick.invoke(showtasklists[position])

        }


    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindValue(datas: ShowTaskListResponse.ViewTask){
            itemView.showprojectid.text = datas.projectid.toString()
            itemView.showtaskid.text = datas.taskid.toString()
            itemView.showsubtaskid.text = datas.subtaskid.toString()

        }
    }

}