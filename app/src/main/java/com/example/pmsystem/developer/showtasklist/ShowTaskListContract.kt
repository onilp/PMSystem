package com.example.pmsystem.developer.showtasklist

import android.support.v7.widget.RecyclerView
import com.example.pmsystem.adapter.ShowTaskListAdapter
import com.example.pmsystem.model.ShowTaskListResponse
import com.example.pmsystem.model.SubTaskResponse
import com.example.pmsystem.model.TaskListResponse

class ShowTaskListContract {



    interface View{
        fun showTaskListError(message: String?)
        fun passDataToAdapter(showtaskList: List<ShowTaskListResponse.ViewTask>) {
        }
    }

    interface Presenter{
        fun viewIsCreated()

    }


}